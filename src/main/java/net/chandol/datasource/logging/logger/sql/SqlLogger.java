package net.chandol.datasource.logging.logger.sql;

import net.chandol.datasource.config.LoggableDataSourceConfig;
import net.chandol.datasource.sql.parameter.ParameterCollector;

public interface SqlLogger {
    void logSql(LoggableDataSourceConfig config, String templateSql, ParameterCollector parameterCollector);

    void logSql(LoggableDataSourceConfig config, String sql);
}
