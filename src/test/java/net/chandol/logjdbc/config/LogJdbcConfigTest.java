package net.chandol.logjdbc.config;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LogJdbcConfigTest {

    @Test
    public void 기본_property_확인() throws Exception {
        //given
        //when
        LogJdbcConfig config = new LogJdbcConfig();

        //then
        LogJdbcProperties prop = config.getProperties();
        assertThat(prop.getSqlTrimExtraLinebreak(), is(true));
        assertThat(prop.getSqlAutoFormatActive(), is(true));
        assertThat(prop.getSqlAutoFormatSkipFormattedSql(), is(true));
        assertThat(prop.getResultsetMaxlength(), is(10));
    }

    @Test
    public void 기본_property_Override() throws Exception {
        //given
        LogJdbcProperties prop = new LogJdbcProperties();

        //when
        prop.setResultsetMaxlength(10);

        //then
        assertThat(prop.getSqlAutoFormatActive(), is(true));
        assertThat(prop.getResultsetMaxlength(), is(10));
    }
}