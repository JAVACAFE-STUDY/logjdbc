package net.chandol.logjdbc;


import ch.qos.logback.classic.spi.LoggingEvent;
import net.chandol.logjdbc._testhelper.AutoLogInitializer;
import org.junit.Assert;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;

import static net.chandol.logjdbc._fixture.DummyDataSource.getDummyH2DataSource;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;


public class LogJdbcDataSourceIntegrationTest extends AutoLogInitializer {

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
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (2, 'Khilan', 25, 'Delhi', 1500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (3, 'kaushik', 23, 'Kota', 2000.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (4, 'Chaitali', 25, 'Mumbai', 6500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (5, 'Hardik', 27, 'Bhopal', 8500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID, NAME, AGE, ADDRESS, SALARY) VALUES (6, 'Komal', 22, 'MP', 4500.00 );");

        // create preparedStatement
        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE ID=?");
        pstmt.setLong(1, 3);
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
        }

        PreparedStatement pstmt2 = connection.prepareStatement("SELECT * FROM CUSTOMERS");
        ResultSet resultSet2 = pstmt2.executeQuery();

        while (resultSet2.next()) {
        }

        PreparedStatement pstmt3 = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE  NAME=?");
        pstmt3.setString(1, "Hardik");
        ResultSet resultSet3 = pstmt3.executeQuery();

        while (resultSet3.next()) {
        }

        resultSet.close();
        resultSet2.close();
        resultSet3.close();

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