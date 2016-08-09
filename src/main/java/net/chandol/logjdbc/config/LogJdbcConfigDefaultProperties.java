package net.chandol.logjdbc.config;


import java.util.HashMap;
import java.util.Map;

public class LogJdbcConfigDefaultProperties {
    public static Map<String, String> getDefaultPropertiesMap(){
        return new HashMap<String, String>() {{
            put("sql.format.remove.linebreak", "false");
            put("sql.format.auto", "hybrid");
            put("resultset.linenumber.max", "10");
        }};
    }
}
