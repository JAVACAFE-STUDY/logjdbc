package net.chandol.logjdbc.logging.printer.sql.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

// 파라미터 변환시 사용되는 함수 모음.
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
