package net.chandol.datasource.sql.parameter.converter;


import net.chandol.datasource.sql.parameter.Parameter;
import net.chandol.datasource.sql.parameter.type.ParameterType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.chandol.datasource.sql.parameter.converter.util.ConverterUtil.*;
import static net.chandol.datasource.sql.parameter.type.ParameterType.*;

/**
 * 일반적으로 사용되는 파라미터 변환로직
 * 각 SQL 벤더별로 다른 변환방식은 하위클래스에 위임한다.
 */
public class BaseParameterConverter implements ParameterConverter {

    @Override
    public String convert(Parameter parameter) {
        ParameterType type = parameter.getType();
        Object value = parameter.getValue();

        // 작업불가능한 타입은 문자열을 그대로 출력합니다.
        if (!type.isConvertible())
            return "'" + value + "'";

        if (value == null) {
            return "NULL";
        }

        // 각 속성별로 컨버팅을 진행합니다.
        if (type.match(_Null)) {
            return "NULL";
        }
        //
        else if (type.match(_String, _NString)) {
            return strParam((String) value);
        }
        //
        else if (type.match(_Date, _Timestamp, _Time)) {
            Date date = typeCast(value, Date.class);
            return strParam(dateFormat(date, "yyyy/MM/dd HH:mm:ss.SSS"));
        }
        //
        else if (type.match(_Boolean)) {
            return typeCast(value, Boolean.class)  ? "1" : "0";
        }
        //
        else {
            return value.toString();
        }
    }

    @Override
    public final List<String> convert(List<Parameter> parameters) {
        List<String> convertedParameters = new ArrayList<>();
        for(Parameter parameter : parameters){
            convertedParameters.add(this.convert(parameter));
        }

        return convertedParameters;
    }
}
