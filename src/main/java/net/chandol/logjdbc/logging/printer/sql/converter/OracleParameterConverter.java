package net.chandol.logjdbc.logging.printer.sql.converter;


import net.chandol.logjdbc.logging.collector.parameter.Parameter;
import net.chandol.logjdbc.logging.collector.parameter.ParameterType;

import java.util.Date;

import static net.chandol.logjdbc.logging.printer.sql.converter.ConverterUtil.dateFormat;
import static net.chandol.logjdbc.logging.collector.parameter.ParameterType._Date;
import static net.chandol.logjdbc.logging.collector.parameter.ParameterType._Timestamp;

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
