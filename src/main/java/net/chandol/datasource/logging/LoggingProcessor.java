package net.chandol.datasource.logging;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import net.chandol.datasource.config.LoggableDataSourceConfig;
import net.chandol.datasource.sql.parameter.Parameter;
import net.chandol.datasource.sql.parameter.ParameterCollector;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;
import net.chandol.datasource.sql.resultset.ResultSetCollector;
import net.chandol.datasource.sql.resultset.ResultSetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LoggingProcessor {
    private static final Logger logger = LoggerFactory.getLogger("net.chandol.sql");

    public static void logSql(LoggableDataSourceConfig config, String templateSql, ParameterCollector parameterCollector) {
        ParameterConverter converter = config.getConverter();
        List<Parameter> params = parameterCollector.getAll();
        List<String> convertedParams = converter.convert(params);

        // Parameter
        logger.debug(parameterToLog(params, convertedParams));

        // SQL with formatter
        String sql = bind(templateSql, convertedParams);
        String formattedSql = config.getFormatter().format(sql);

        logger.debug(formattedSql);
    }

    public static void logSql(LoggableDataSourceConfig config, String sql) {
        //SQL Formatting
        String formattedSql = config.getFormatter().format(sql);

        if (logger.isDebugEnabled()) {
            logger.debug(formattedSql);
        }
    }

    public static void logResultSet(LoggableDataSourceConfig config, ResultSetCollector collector) {
        ResultSetData resultSetData = collector.getResultSetData();

        List<String> columns = resultSetData.getColumns();
        List<String[]> rowValues = resultSetData.getDatas();

        V2_AsciiTable table = new V2_AsciiTable();
        table.addRow(columns.toArray());
        table.addRule();

        for (String[] values : rowValues) {
            table.addRow(values);
        }

        V2_AsciiTableRenderer rend = new V2_AsciiTableRenderer();
        rend.setTheme(V2_E_TableThemes.UTF_HEAVY.get());
        rend.setWidth(new WidthLongestLine());

        logger.debug("\n" + rend.render(table).toString());
    }

    // 파라미터가 모호함... 리팩토링 필요!!
    static String parameterToLog(List<Parameter> params, List<String> convertedParams) {
        StringBuilder builder = new StringBuilder();
        builder.append("parameters : [");
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

    private static String bind(String templateSql, List<String> params) {
        // TODO 성능 개선필요
        for (String param : params)
            templateSql = templateSql.replace("?", param);

        return templateSql;
    }

}
