package net.chandol.logjdbc.logging.printer.sql.formatter;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class SqlFormatterTest {
    @Test
    public void testFormat() throws Exception {
        //given
        SqlFormatter sqlFormatter = DefaultSqlFormatter.getInstance();
        String sourceSql = "SELECT * FROM EMP WHERE DEPTNO = 10 AND NAME = '테스트';";

        //when
        String resultSql = sqlFormatter.format(sourceSql);

        //then
        //TODO 포매터 검증 좀 더 정확하게 할 것
        Assert.assertThat(sourceSql, is(not(resultSql)));
    }

}