package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc.logging.LogContext;

public interface SqlPrinter {
    void printParameter(LogContext context);

    void printSql(LogContext context);
}
