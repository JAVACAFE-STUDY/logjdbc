package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;

public interface SqlPrinter {
    void logSql(LogJdbcConfig config, String templateSql, ParameterCollector parameterCollector);

    void logSql(LogJdbcConfig config, String sql);
}
