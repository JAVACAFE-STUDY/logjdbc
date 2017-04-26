package net.chandol.logjdbc.logging.collector.parameter;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static net.chandol.logjdbc.logging.collector.parameter.ParameterType.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ParameterCollectorTest {
    @Test
    public void addAndGet() throws Exception {
        //given
        ParameterCollector paramCollector = new ParameterCollector();

        //when
        paramCollector.add(1, _String, "TEST");
        paramCollector.add(2, _Timestamp, new Date());
        paramCollector.add(3, _Int, 123);

        //then
        List<Parameter> result = paramCollector.getAll();

        assertThat(result.size(), is(3));
        assertThat(result.get(0), is(Parameter.of(0, _String, "TEST")));
    }
}