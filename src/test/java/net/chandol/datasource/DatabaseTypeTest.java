package net.chandol.datasource;

import net.chandol.datasource.sql.parameter.converter.BaseParameterConverter;
import net.chandol.datasource.sql.parameter.converter.MysqlParameterConverter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;
import org.junit.Test;

import static net.chandol.datasource.DatabaseType.H2;
import static net.chandol.datasource.DatabaseType.MYSQL;
import static net.chandol.datasource.testhelper.DummyDataSource.getDummyH2DataSource;
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

    // TODO 다른 데이터베이스 타입도 정상적으로 찾아지는지 확인필요
    // Mock을 설정하는 건 창화씨에게 물어볼 것.
    @Test
    public void findDatabaseTest() throws Exception {
        //given

        //when
        DatabaseType type = DatabaseType.find(getDummyH2DataSource());

        //then
        assertThat(type).isSameAs(H2);
    }
}