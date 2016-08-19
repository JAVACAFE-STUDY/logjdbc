package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.config.LogJdbcProperties;
import net.chandol.logjdbc.logging.collector.parameter.Parameter;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;
import net.chandol.logjdbc.logging.printer.sql.paramconverter.ParameterConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultSqlPrinter implements SqlPrinter {
    /* logger */
    private static final Logger sqlLogger = LoggerFactory.getLogger("net.chandol.logjdbc.sql");
    private static final Logger paramLogger = LoggerFactory.getLogger("net.chandol.logjdbc.parameter");

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
    public void logSql(LogJdbcConfig config,
                       String templateSql,
                       ParameterCollector parameterCollector) {

        ParameterConverter converter = config.getConverter();
        List<Parameter> params = parameterCollector.getAll();
        List<String> convertedParams = converter.convert(params);

        // Parameter
        paramLogger.debug(parameterToLog(params, convertedParams));

        // SQL with formatter
        // FIXME 이부분은 정리 및 중복제거 필요
        String sql = SqlParameterBinder.bind(templateSql, convertedParams);

        if (checkFormattable(config.getProperties(), sql))
            sql = config.getFormatter().format(sql);
        else
            sql = "\n" + sql;


        // FIXME 아래 부분은 필터 형태로 변경하자.
        if(config.getProperties().getSqlTrimExtraLinebreak()){
            sql = removeExtraLineBreak(sql);
        }

        sqlLogger.debug(sql);
    }

    String removeExtraLineBreak(String sql) {
        sql = sql.replaceAll("(\n){2,}", "\n");
        return sql;
    }

    @Override
    public void logSql(LogJdbcConfig config, String sql) {
        //SQL Formatting
        // FIXME 이부분은 정리 및 중복제거 필요
        if (checkFormattable(config.getProperties(), sql))
            sql = config.getFormatter().format(sql);
        else
            sql = "\n" + sql;

        sqlLogger.debug(sql);
    }

    // FIXME 파라미터가 모호함... 리팩토링 필요!!
    static String parameterToLog(List<Parameter> params, List<String> convertedParams) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nparameters : [");
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

    private static boolean isFormattedSql(String sql) {
        return sql.contains("\n");
    }

    private static boolean not(boolean condition) {
        return !condition;
    }
}
