package net.chandol.datasource.sql.parameter.converter;

import net.chandol.datasource.except.LoggableDataSourceException;
import net.chandol.datasource.sql.parameter.Parameter;

import java.util.List;

/**
 * 작업필요 컨버터
 */
public class NeedImplementParameterConverter implements ParameterConverter{
    @Override
    public String convert(Parameter parameter) {
        throw new LoggableDataSourceException("Not yet Implemented method.");
    }

    @Override
    public List<String> convert(List<Parameter> parameters) {
        throw new LoggableDataSourceException("Not yet Implemented method.");
    }
}
