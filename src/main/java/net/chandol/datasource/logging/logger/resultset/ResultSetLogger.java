package net.chandol.datasource.logging.logger.resultset;

import net.chandol.datasource.config.LoggableDataSourceConfig;
import net.chandol.datasource.resultset.ResultSetCollector;

public interface ResultSetLogger {
    void logResultSet(LoggableDataSourceConfig config, ResultSetCollector collector);
}
