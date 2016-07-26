package net.chandol.datasource.sql;

import java.util.List;

public class SqlParmeterBinder {

    public static String bind(String templateSql, List<String> params) {
        // TODO 성능 개선필요
        for (String param : params)
            templateSql = templateSql.replace("?", param);

        return templateSql;
    }

}
