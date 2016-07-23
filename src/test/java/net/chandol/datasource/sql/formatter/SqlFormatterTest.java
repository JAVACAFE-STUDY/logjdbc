package net.chandol.datasource.sql.formatter;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class SqlFormatterTest {
    @Test
    public void testFormat() throws Exception {
        //given
        SqlFormatter sqlFormatter = new DefaultSqlFormatter();
        String sourceSql = "SELECT * FROM EMP WHERE DEPTNO = 10 AND NAME = '테스트';";

        //when
        String resultSql = sqlFormatter.format(sourceSql);

        //then
        Assertions.assertThat(resultSql)
                .asString()
                .hasLineCount(3);
    }

}