package net.chandol.logjdbc.logging.printer.sql.paramconverter;

import net.chandol.logjdbc.logging.collector.parameter.Parameter;
import net.chandol.logjdbc.logging.collector.parameter.ParameterType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BaseParameterConverterTest {
    @Test
    public void convert() throws Exception {
        //given
        ParameterConverter converter = new BaseParameterConverter();

        //when
        String testParam = converter.convert(Parameter.of(ParameterType._String, "테스트"));

        //then
        assertThat(testParam, is("'테스트'"));
    }
}