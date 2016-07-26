package net.chandol.datasource.logging;

import net.chandol.datasource.config.LoggableDataSourceConfig;
import net.chandol.datasource.resultset.ResultSetCollector;
import net.chandol.datasource.sql.parameter.ParameterCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingProcessor {
    private static final Logger logger = LoggerFactory.getLogger("net.chandol.sql");

    public static void logSql(LoggableDataSourceConfig config, String templateSql, ParameterCollector parameterCollector) {
        config.getSqlLogger().logSql(config, templateSql, parameterCollector);
    }

    public static void logSql(LoggableDataSourceConfig config, String sql) {
        config.getSqlLogger().logSql(config, sql);
    }

    public static void logResultSet(LoggableDataSourceConfig config, ResultSetCollector collector) {
        config.getResultSetLogger().logResultSet(config, collector);
    }
}
