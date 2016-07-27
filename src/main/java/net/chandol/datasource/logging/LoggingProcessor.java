package net.chandol.datasource.logging;

import net.chandol.datasource.config.LoggableDataSourceConfig;
import net.chandol.datasource.resultset.ResultSetCollector;
import net.chandol.datasource.sql.parameter.ParameterCollector;

public class LoggingProcessor {
    public static void logSql(LoggableDataSourceConfig config, String templateSql, ParameterCollector parameterCollector) {
        config.getSqlLogger().logSql(config, templateSql, parameterCollector);
    }

    public static void logSql(LoggableDataSourceConfig config, String sql) {
        config.getSqlLogger().logSql(config, sql);
    }

    public static void logResultSet(LoggableDataSourceConfig config, ResultSetCollector collector) {
        config.getResultSetLogger().logResultSet(config, collector);
    }

    public static String ResultSetToString(LoggableDataSourceConfig config, ResultSetCollector collector) {
        return config.getResultSetLogger().ResultSetToString(config, collector);
    }
}
