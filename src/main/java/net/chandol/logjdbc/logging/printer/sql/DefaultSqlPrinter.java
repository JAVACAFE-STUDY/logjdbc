package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.config.LogJdbcProperties;
import net.chandol.logjdbc.logging.LogContext;
import net.chandol.logjdbc.logging.collector.parameter.Parameter;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;
import net.chandol.logjdbc.logging.printer.sql.paramconverter.ParameterConverter;
import org.slf4j.Logger;

import java.util.List;

public class DefaultSqlPrinter implements SqlPrinter {
    /* singleton */
    private static DefaultSqlPrinter defaultSqlPrinter;

    public static DefaultSqlPrinter getInstance() {
        if (defaultSqlPrinter == null)
            defaultSqlPrinter = new DefaultSqlPrinter();

        return defaultSqlPrinter;
    }

    private DefaultSqlPrinter() {
    }

    @Override
    public void printParameter(LogContext context) {
        ParameterCollector collector = context.getParameterCollector();
        ParameterConverter converter = context.getConfig().getConverter();

        List<Parameter> params = collector.getAll();
        List<String> convertedParams = converter.convert(params);

        // Parameter
        getLogger(context, "parameter").debug(
                parameterToLog(params, convertedParams)
        );
    }

    @Override
    public void printSql(LogContext context) {
        LogJdbcConfig config = context.getConfig();
        String sql = context.getSql();

        if (context.getParameterCollector() != null) {
            ParameterCollector collector = context.getParameterCollector();
            ParameterConverter converter = config.getConverter();

            List<Parameter> params = collector.getAll();
            List<String> convertedParams = converter.convert(params);

            sql = SqlParameterBinder.bind(sql, convertedParams);
        }

        // FIXME 아래 부분은 필터 형태로 변경하자.
        if (checkFormattable(config.getProperties(), sql))
            sql = config.getFormatter().format(sql);
        else
            sql = "\n" + sql;

        if (config.getProperties().getSqlTrimExtraLinebreak()) {
            sql = removeExtraLineBreak(sql);
        }

        getLogger(context, "sql").debug(sql);
    }

    String removeExtraLineBreak(String sql) {
        sql = sql.replaceAll("(\n){2,}", "\n");
        return sql;
    }

    // FIXME 파라미터가 모호함... 리팩토링 필요!!
    static String parameterToLog(List<Parameter> params, List<String> convertedParams) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n    parameters : [");
        for (int idx = 0; idx < params.size(); idx++) {
            String type = params.get(idx).getType().getTypeAsStr();
            String value = convertedParams.get(idx);

            builder.append("{").append(type).append(" = ").append(value).append("}");

            if ((params.size() - 1) != idx)
                builder.append(", ");
        }
        builder.append("]");

        return builder.toString();
    }

    private static class SqlParameterBinder {
        static String bind(String templateSql, List<String> params) {
            // TODO 성능 개선필요
            for (String param : params)
                templateSql = templateSql.replaceFirst("\\?", param);

            return templateSql;
        }
    }

    // FIXME Properties 설정은 고민 필요!
    private static boolean checkFormattable(LogJdbcProperties prop, String sql) {
        boolean isFormatActive = prop.getSqlAutoFormatActive();
        boolean isIgnoreFormattedSql = prop.getSqlAutoFormatSkipFormattedSql();

        if (isFormatActive)
            return not(isIgnoreFormattedSql && isFormattedSql(sql));
        else
            return false;
    }

    private static Logger getLogger(LogContext context, String parameter) {
        return context.getHelper().getLogger(parameter);
    }

    private static boolean isFormattedSql(String sql) {
        return sql.contains("\n");
    }

    private static boolean not(boolean condition) {
        return !condition;
    }
}
