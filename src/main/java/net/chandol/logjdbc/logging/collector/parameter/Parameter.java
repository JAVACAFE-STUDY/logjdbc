package net.chandol.logjdbc.logging.collector.parameter;

public class Parameter implements Comparable<Parameter>{
    private int index;
    private Object value;
    private ParameterType type;

    private Parameter(int index, ParameterType type, Object value) {
        this.index = index;
        this.value = value;
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public ParameterType getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    public static Parameter of(int index, ParameterType type, Object value){
        return new Parameter(index, type, value);
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

    @Override
    public int compareTo(Parameter b) {
        return Integer.compare(this.getIndex(), b.getIndex());
    }
}