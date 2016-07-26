package net.chandol.datasource.sql.parameter;

import net.chandol.datasource.sql.parameter.type.ParameterType;

public class Parameter {
    private Object value;
    private ParameterType type;

    public Parameter(ParameterType type, Object value) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public ParameterType getType() {
        return type;
    }
}