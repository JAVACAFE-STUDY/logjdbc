package net.chandol.logjdbc.except;

/**
 * 에러 통합처리
 */
public class LoggableDataSourceException extends RuntimeException{

    public LoggableDataSourceException(String message) {
        super(message);
    }

    public LoggableDataSourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoggableDataSourceException(Throwable cause) {
        super(cause);
    }

    public LoggableDataSourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
