package net.chandol.logjdbc.config;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LogJdbcConfigTest {

    @Test
    public void 기본_property_확인() throws Exception {
        //given
        //when
        LogJdbcConfig config = new LogJdbcConfig();

        //then
        assertThat(config.getBooleanProperty("sql.trim.extra-linebreaks"), is(true));
        assertThat(config.getBooleanProperty("sql.auto.format.active"), is(true));
        assertThat(config.getBooleanProperty("sql.auto.format.ignore-formatted"), is(true));
        assertThat(config.getIntProperty("resultset.maxlength"), is(10));
    }

    @Test
    public void 기본_property_Override() throws Exception {
        //given
        Map<String, String> properties = new HashMap<String, String>() {{
            put("sql.auto.format.active", "false");
        }};

        //when
        LogJdbcConfig config = new LogJdbcConfig(properties);

        //then
        assertThat(config.getBooleanProperty("sql.auto.format.active"), is(false));
        assertThat(config.getBooleanProperty("sql.trim.extra-linebreaks"), is(true));


    }
}