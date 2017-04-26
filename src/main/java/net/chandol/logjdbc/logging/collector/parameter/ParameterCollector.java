package net.chandol.logjdbc.logging.collector.parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 로깅에 필요한 파라미터를 모은다.
 */
public class ParameterCollector {
    private List<Parameter> parameters = new ArrayList<>();

    public void add(int index, ParameterType type, Object value) {
        Parameter param = Parameter.of(index - 1, type, value);
        parameters.add(param);
    }

    public void add(int index, ParameterType type, String description) {
        Parameter param = Parameter.of(index - 1, type, description);
        parameters.add(param);
    }

    public List<Parameter> getAll(){
        Collections.sort(parameters);
        return parameters;
    }
}
