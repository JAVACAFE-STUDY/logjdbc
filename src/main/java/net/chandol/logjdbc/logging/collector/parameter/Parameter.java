package net.chandol.logjdbc.logging.collector.parameter;

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