package net.chandol.datasource.sql.parameter.converter.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ConverterUtil {
    public static String dateFormat(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String strParam(String source) {
        return "'" + source.replaceAll("'", "\'") + "'";
    }

    public static <T> T typeCast(Object o, Class<T> clazz) {
        return clazz.cast(o);
    }

}
