package net.chandol.logjdbc.config;


import java.util.HashMap;
import java.util.Map;

public class LogJdbcConfigDefaultProperties {
    public static Map<String, String> getDefaultPropertiesMap(){
        return new HashMap<String, String>() {{
            put("sql.trim.extra-linebreaks", "true");
            put("sql.auto.format.active", "true");
            put("sql.auto.format.ignore-formatted", "true");
            put("resultset.maxlength", "10");
        }};
    }
}
