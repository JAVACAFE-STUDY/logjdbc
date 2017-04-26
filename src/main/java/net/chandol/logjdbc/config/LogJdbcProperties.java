package net.chandol.logjdbc.config;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.valueOf;

public class LogJdbcProperties {
    public static final String SQL_TRIM_EXTRA_LINEBREAK = "sql.trim.extra-linebreaks";
    public static final String SQL_AUTO_FORMAT_ACTIVE = "sql.auto.format.active";
    public static final String SQL_AUTO_FORMAT_SKIP_FORMATTED_SQL = "sql.auto.format.skip-formatted";
    public static final String RESULTSET_MAXLENGTH = "resultset.maxlength";
    public static final String LOGGER_BASE_PATH = "logger.base.path";

    private Map<String, String> properties;

    public LogJdbcProperties() {
        this.properties = new HashMap<>();
        setSqlTrimExtraLinebreak(true);
        setSqlAutoFormatActive(true);
        setSqlAutoFormatSkipFormattedSql(true);
        setResultsetMaxlength(10);
        setLoggerBasePath("net.chandol.logjdbc");
    }

    public void setSqlTrimExtraLinebreak(boolean value) {setProperty(SQL_TRIM_EXTRA_LINEBREAK, value);}
    public boolean getSqlTrimExtraLinebreak() {return getPropertyAsBoolean(SQL_TRIM_EXTRA_LINEBREAK);}

    public void setSqlAutoFormatActive(boolean value){
        setProperty(SQL_AUTO_FORMAT_ACTIVE, value);
    }
    public boolean getSqlAutoFormatActive() {
        return getPropertyAsBoolean(SQL_AUTO_FORMAT_ACTIVE);
    }

    public void setSqlAutoFormatSkipFormattedSql(boolean value){
        setProperty(SQL_AUTO_FORMAT_SKIP_FORMATTED_SQL, value);
    }
    public boolean getSqlAutoFormatSkipFormattedSql() {return getPropertyAsBoolean(SQL_AUTO_FORMAT_SKIP_FORMATTED_SQL);}

    public void setResultsetMaxlength(int value){setProperty(RESULTSET_MAXLENGTH, value);}
    public int getResultsetMaxlength() {return getPropertyAsInteger(RESULTSET_MAXLENGTH);}

    public void setLoggerBasePath(String basePath){setProperty(LOGGER_BASE_PATH, basePath);}
    public String getLoggerBasePath() {return getProperty(LOGGER_BASE_PATH);}

    /* Properties 범용 메서드들 */
    public boolean isExist(String key) {
        return this.properties.containsKey(key);
    }

    public void setProperty(String key, String value) {
        this.properties.put(key, value);
    }

    public void setProperty(String key, boolean value) {
        this.properties.put(key, String.valueOf(value));
    }

    public void setProperty(String key, int value) {
        this.properties.put(key, String.valueOf(value));
    }

    public String getProperty(String key) {
        return this.properties.get(key);
    }

    public boolean getPropertyAsBoolean(String key) {
        String value = getProperty(key);
        if (value.equalsIgnoreCase("true"))
            return true;
        else if (value.equalsIgnoreCase("false"))
            return false;
        else
            throw new IllegalArgumentException("cannot convert to boolean : " + value);
    }

    public int getPropertyAsInteger(String key) {
        try {
            return valueOf(getProperty(key));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
