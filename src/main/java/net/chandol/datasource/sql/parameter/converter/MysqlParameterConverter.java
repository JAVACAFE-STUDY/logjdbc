package net.chandol.datasource.sql.parameter.converter;


import net.chandol.datasource.sql.parameter.Parameter;
import net.chandol.datasource.sql.parameter.type.ParameterType;

import java.util.Date;

import static net.chandol.datasource.sql.parameter.converter.util.ConverterUtil.dateFormat;
import static net.chandol.datasource.sql.parameter.converter.util.ConverterUtil.strParam;
import static net.chandol.datasource.sql.parameter.type.ParameterType.*;

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
