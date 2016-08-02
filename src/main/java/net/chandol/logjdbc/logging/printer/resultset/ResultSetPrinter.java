package net.chandol.logjdbc.logging.printer.resultset;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;

public interface ResultSetPrinter {
    void logResultSet(LogJdbcConfig config, ResultSetCollector collector);
}
