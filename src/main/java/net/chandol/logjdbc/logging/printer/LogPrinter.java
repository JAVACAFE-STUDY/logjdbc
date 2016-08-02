package net.chandol.logjdbc.logging.printer;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;

public class LogPrinter {
    public static void logSql(LogJdbcConfig config, String templateSql, ParameterCollector parameterCollector) {
        config.getSqlPrinter().logSql(config, templateSql, parameterCollector);
    }

    public static void logSql(LogJdbcConfig config, String sql) {
        config.getSqlPrinter().logSql(config, sql);
    }

    public static void logResultSet(LogJdbcConfig config, ResultSetCollector collector) {
        config.getResultSetPrinter().logResultSet(config, collector);
    }
}
