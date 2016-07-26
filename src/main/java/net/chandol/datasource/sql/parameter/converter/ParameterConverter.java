package net.chandol.datasource.sql.parameter.converter;

import net.chandol.datasource.sql.parameter.Parameter;

import java.util.List;

/**
 * 파라미터 컨버터는 별도의 설정이 필요없어보인다.
 * 항상 동일한 객체를 제공해준다고 하더라도 큰 문제는 없어보인다.
 * 문제는 인터페이스이므로 생성자를 변경할수 없다.
 */
public interface ParameterConverter {
    String convert(Parameter parameter);

    List<String> convert(List<Parameter> parameters);
}
