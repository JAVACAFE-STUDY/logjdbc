package net.chandol.datasource;

import net.chandol.datasource.sql.parameter.converter.BaseParameterConverter;
import net.chandol.datasource.sql.parameter.converter.MysqlParameterConverter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;
import org.junit.Test;

import static net.chandol.datasource.DatabaseType.H2;
import static net.chandol.datasource.DatabaseType.MYSQL;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseTypeTest {

    @Test
    public void 타입별_컨버터_반환테스트() throws Exception {
        //given

        //when
        ParameterConverter h2Converter1 = H2.getParameterConverter();
        ParameterConverter h2Converter2 = H2.getParameterConverter();
        ParameterConverter mySqlConverter = MYSQL.getParameterConverter();

        //then
        assertThat(h2Converter1)
                .isInstanceOf(BaseParameterConverter.class);

        assertThat(mySqlConverter)
                .isInstanceOf(MysqlParameterConverter.class);

        // then : converter는 싱글톤을 반환한다.
        // 두개의 참조값은 동일하여야 한다.
        assertThat(h2Converter1).isSameAs(h2Converter2);
    }

    @Test
    public void findDatabaseTest() throws Exception {

    }

    @Test
    public void getDatabaseName() throws Exception {

    }

}