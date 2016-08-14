package net.chandol.logjdbc.logging.printer.sql;

import net.chandol.logjdbc._testhelper.LogReadableTestBase;
import net.chandol.logjdbc.config.LogJdbcConfig;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DefaultSqlPrinterTest extends LogReadableTestBase {
    @Test
    public void SQL포메팅테스트() throws Exception {
        //given
        DefaultSqlPrinter sqlPrinter = DefaultSqlPrinter.getInstance();
        LogJdbcConfig config = new LogJdbcConfig();

        //when
        sqlPrinter.logSql(config, "SELECT * FROM DUAL");

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("\n    SELECT"));
    }

    @Test
    public void 이미_포메팅된_SQL은_포메팅하지_않는다() throws Exception {
        //given
        DefaultSqlPrinter sqlPrinter = DefaultSqlPrinter.getInstance();
        LogJdbcConfig config = new LogJdbcConfig();

        //when
        sqlPrinter.logSql(config, "SELECT * \nFROM DUAL");

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("SELECT * \nFROM DUAL"));
    }

    @Test
    public void format기능은Properties로끌수있다() throws Exception {
        //given
        DefaultSqlPrinter sqlPrinter = DefaultSqlPrinter.getInstance();
        Map<String, String> propMap = new HashMap<String, String>(){{
            put("sql.auto.format.active", "false");
        }};
        LogJdbcConfig config = new LogJdbcConfig(propMap);

        //when
        sqlPrinter.logSql(config, "SELECT * FROM DUAL");

        //then
        String formattedSql = getLogMessages().get(0);
        assertThat(formattedSql, containsString("SELECT * FROM DUAL"));
    }

    @Test
    public void 여러줄의개행은_하나로_변경(){
        //given
        DefaultSqlPrinter printer = DefaultSqlPrinter.getInstance();
        String source = "\n\n\n\n안녕\n반갑습니다.\n\n\n\n\n\n개행치환하기";

        //when
        String result = printer.removeExtraLineBreak(source);

        //then
        assertThat(result, is("\n안녕\n반갑습니다.\n개행치환하기"));
    }

}