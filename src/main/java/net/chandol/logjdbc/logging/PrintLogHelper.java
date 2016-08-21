package net.chandol.logjdbc.logging;

import net.chandol.logjdbc.config.LogJdbcConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final public class PrintLogHelper {
    private LogJdbcConfig config;

    public PrintLogHelper(LogJdbcConfig config) {
        this.config = config;
    }

    public Logger getLogger(String subfix){
        String basePath = config.getProperties().getLoggerBasePath();
        return LoggerFactory.getLogger(basePath + "." + subfix);
    }
}
