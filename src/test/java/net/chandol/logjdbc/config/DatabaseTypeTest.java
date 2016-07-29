package net.chandol.logjdbc.config;

import net.chandol.logjdbc.logging.printer.sql.converter.BaseParameterConverter;
import net.chandol.logjdbc.logging.printer.sql.converter.MysqlParameterConverter;
import net.chandol.logjdbc.logging.printer.sql.converter.ParameterConverter;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static net.chandol.logjdbc.config.DatabaseType.*;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class DatabaseTypeTest {

    @Test
    public void 타입별_컨버터_반환테스트() throws Exception {
        //given

        //when
        ParameterConverter h2Converter = H2.getParameterConverter();
        ParameterConverter mySqlConverter = MYSQL.getParameterConverter();

        //then
        assertThat(h2Converter, instanceOf(BaseParameterConverter.class));
        assertThat(mySqlConverter, instanceOf(MysqlParameterConverter.class));
    }

    @Test
    public void 컨버터는_싱글톤으로_등록된다() {
        //given
        ParameterConverter h2Converter1 = H2.getParameterConverter();
        ParameterConverter h2Converter2 = H2.getParameterConverter();

        //then
        assertThat(h2Converter1, CoreMatchers.sameInstance(h2Converter2));
    }

    @Test
    public void 데이터베이스_타입_찾기() throws Exception {
        //given
        DataSource H2DataSource = getMockDatasourceWithDatabaseName("H2");
        DataSource OracleDataSource = getMockDatasourceWithDatabaseName("Oracle");
        DataSource UnknownDataSource = getMockDatasourceWithDatabaseName("Tibero");

        //when
        DatabaseType h2Type = DatabaseType.find(H2DataSource);
        DatabaseType oracleType = DatabaseType.find(OracleDataSource);
        DatabaseType tiberoType = DatabaseType.find(UnknownDataSource);

        //then
        assertThat(h2Type, is(H2));
        assertThat(oracleType, is(ORACLE));
        assertThat(tiberoType, is(UNKNOWN));
    }

    private DataSource getMockDatasourceWithDatabaseName(String name) throws SQLException {
        DataSource mockDataSource = Mockito.mock(DataSource.class);
        Connection mockConnection = Mockito.mock(Connection.class);
        DatabaseMetaData mockMetaData = Mockito.mock(DatabaseMetaData.class);

        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.getMetaData()).thenReturn(mockMetaData);
        Mockito.when(mockMetaData.getDatabaseProductName()).thenReturn(name);

        return mockDataSource;
    }
}