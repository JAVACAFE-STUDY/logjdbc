package net.chandol.datasource.sql.parameter;

import net.chandol.datasource.sql.parameter.type.ParameterType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParameterCollector {
    private List<Parameter> parameters = new LinkedList<>();

    public void add(int index, ParameterType type, Object value) {
        parameters.add(index - 1, new Parameter(type, value));
    }

    public void add(int index, ParameterType type, String description) {
        parameters.add(index - 1, new Parameter(type, description));
    }

    public List<Parameter> getAll(){
        return new ArrayList<>(parameters);
    }
}
