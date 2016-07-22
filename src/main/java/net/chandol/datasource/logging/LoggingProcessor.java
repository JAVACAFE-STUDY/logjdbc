package net.chandol.datasource.logging;

import net.chandol.datasource.LoggableDataSourceConfig;
import net.chandol.datasource.sql.parameter.Parameter;
import net.chandol.datasource.sql.parameter.ParameterCollector;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;

import java.util.List;

/**
 * 어서 작업 끝내고 로깅을 넣읍시다!
 */
public class LoggingProcessor {
    public static void logSql(LoggableDataSourceConfig config, String templateSql, ParameterCollector parameterCollector) {

        ParameterConverter converter = config.getConverter();
        List<Parameter> params = parameterCollector.getAll();
        List<String> convertedParams = converter.convert(params);

        // Parameter
        System.out.println(parameterToLog(params, convertedParams));

        // SQL
        String sql = bind(templateSql, convertedParams);

        //SQL Formatting
        String formattedSql = config.getFormatter().format(sql);

        System.out.println(formattedSql);
    }

    public static void logSql(LoggableDataSourceConfig config, String sql) {
        //SQL Formatting
        String formattedSql = config.getFormatter().format(sql);

        System.out.println(formattedSql);
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
