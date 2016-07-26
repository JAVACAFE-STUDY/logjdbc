package net.chandol.datasource.sql.parameter.converter;


import net.chandol.datasource.sql.parameter.Parameter;
import net.chandol.datasource.sql.parameter.type.ParameterType;

import java.util.Date;

import static net.chandol.datasource.sql.parameter.converter.util.ConverterUtil.dateFormat;
import static net.chandol.datasource.sql.parameter.type.ParameterType._Date;
import static net.chandol.datasource.sql.parameter.type.ParameterType._Timestamp;

public class OracleParameterConverter extends BaseParameterConverter {

    @Override
    public String convert(Parameter parameter) {
        ParameterType type = parameter.getType();
        Object value = parameter.getValue();

        if (type.match(_Timestamp)) {
            String formattedDate = dateFormat((Date) value, "yyyy/MM/dd HH:mm:ss.SSS");
            return "to_timestamp('" + formattedDate + "', 'yyyy/mm/dd hh24:mi:ss.ff3')";
        } else if (type.match(_Date)) {
            String formattedDate = dateFormat((Date) value, "yyyy/MM/dd HH:mm:ss");
            return "to_date('" + formattedDate + "', 'yyyy/mm/dd hh24:mi:ss')";
        }

        return super.convert(parameter);
    }
}
