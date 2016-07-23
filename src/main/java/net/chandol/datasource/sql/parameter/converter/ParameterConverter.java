package net.chandol.datasource.sql.parameter.converter;

import net.chandol.datasource.sql.parameter.Parameter;

import java.util.List;

public interface ParameterConverter {
    String convert(Parameter parameter);

    List<String> convert(List<Parameter> parameters);
}
