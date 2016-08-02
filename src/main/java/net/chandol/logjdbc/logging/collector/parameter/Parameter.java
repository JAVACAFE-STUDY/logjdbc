package net.chandol.logjdbc.logging.collector.parameter;

public class Parameter {
    private Object value;
    private ParameterType type;

    private Parameter(ParameterType type, Object value) {
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public ParameterType getType() {
        return type;
    }

    public static Parameter of(ParameterType type, Object value){
        return new Parameter(type, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parameter)) return false;

        Parameter parameter = (Parameter) o;

        if (value != null ? !value.equals(parameter.value) : parameter.value != null) return false;
        return type == parameter.type;
    }

    // hashcode는 자동생성처리
    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}