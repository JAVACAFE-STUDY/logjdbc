package net.chandol.logjdbc;


import ch.qos.logback.classic.spi.LoggingEvent;
import net.chandol.logjdbc._testhelper.LogReadableTestBase;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;

import static net.chandol.logjdbc._fixture.DummyDataSource.getDummyH2DataSource;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class LogJdbcDataSourceIntegrationTest extends LogReadableTestBase {

    @Test
    public void coreIntegrationTest() throws SQLException {
        // create datasource
        DataSource h2DataSource = getDummyH2DataSource();
        DataSource dataSource = new LogJdbcDataSource(h2DataSource);

        // open connection
        Connection connection = dataSource.getConnection();

        // create statement
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE CUSTOMERS (ID INT NOT NULL, NAME VARCHAR (20) NOT NULL, AGE INT NOT NULL, ADDRESS CHAR (25), SALARY DECIMAL(18, 2), PRIMARY KEY (ID));");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (2, '김동표', 25, '서울', 1500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (3, '송준호', 23, '부산', 2000.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (4, '문종현', 25, '대전', 6500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (5, '심인보', 27, '대구', 8500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (6, '박세종', 22, '울산', 4500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (7, 'Tester', 21, 'Texas', 1500.00 );");

        // create preparedStatement
        PreparedStatement pstmt1 = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE ID=?");
        pstmt1.setLong(1, 3);
        ResultSet resultSet1 = pstmt1.executeQuery();

        while (resultSet1.next()) {
        }

        PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM CUSTOMERS");
        ResultSet resultSet2 = pstmt2.executeQuery();

        while (resultSet2.next()) {
        }

        PreparedStatement pstmt3 = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE NAME=?");
        pstmt3.setString(1, "문종현");
        ResultSet resultSet3 = pstmt3.executeQuery();

        while (resultSet3.next()) {
        }


        PreparedStatement pstmt4 = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE NAME=?");
        pstmt4.setObject(1, "Tester");
        ResultSet resultSet4 = pstmt4.executeQuery();

        while (resultSet4.next()) {
        }

        resultSet1.close();
        resultSet2.close();
        resultSet3.close();
        resultSet4.close();

        connection.rollback();
        connection.close();
    }

    // TODO 단위테스트이므로 파일 분리할 것
    @Test
    public void 잘못된_SQL입력시_에러처리() throws SQLException {
        // create datasource
        DataSource h2DataSource = getDummyH2DataSource();
        DataSource dataSource = new LogJdbcDataSource(h2DataSource);

        // open connection
        Connection connection = dataSource.getConnection();

        // create preparedStatement
        // expected SQLException
        try {
            connection.prepareStatement("SELECT * FROM CUSTOMERS AND ?");

            // test fail when not throw SQLException
            Assert.fail();
        } catch (SQLException e) {
            LoggingEvent lastLog = getRecentLogs(1).get(0);

            // expected SqlLog
            assertThat(lastLog.getMessage(), containsString("CUSTOMERS"));
        }
    }
}