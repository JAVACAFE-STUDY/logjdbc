package net.chandol.logjdbc.logging.printer.sql.converter;


import net.chandol.logjdbc.logging.collector.parameter.Parameter;
import net.chandol.logjdbc.logging.collector.parameter.ParameterType;

import java.util.Date;

import static net.chandol.logjdbc.logging.printer.sql.converter.ConverterUtil.dateFormat;
import static net.chandol.logjdbc.logging.printer.sql.converter.ConverterUtil.strParam;
import static net.chandol.logjdbc.logging.collector.parameter.ParameterType.*;

/**
 * Mysql 벤더용 파라미터 변환
 */
public class MysqlParameterConverter extends BaseParameterConverter {
    @Override
    public String convert(Parameter parameter) {
        ParameterType type = parameter.getType();
        Object value = parameter.getValue();

        if (type.match(_Time))
            return strParam(dateFormat((Date) value, "HH:mm:ss"));
        if (type.match(_Date))
            return strParam(dateFormat((Date) value, "yyyy-MM-dd"));
        if (type.match(_Timestamp))
            return strParam(dateFormat((Date) value, "yyyy-MM-dd HH:mm:ss"));

        return super.convert(parameter);
    }
}
