package net.chandol.datasource;


import org.h2.jdbcx.JdbcDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.*;

public class LoggableDataSourceTest {
    @Test
    public void core() throws SQLException {
        DataSource h2DataSource = getH2DataSource();
        DataSource dataSource = new LoggableDataSource(h2DataSource);

        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE CUSTOMERS(   ID   INT              NOT NULL,   NAME VARCHAR (20)     NOT NULL,   AGE  INT              NOT NULL,   ADDRESS  CHAR (25) ,   SALARY   DECIMAL (18, 2),   PRIMARY KEY (ID));");
        statement.execute("INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (2, 'Khilan', 25, 'Delhi', 1500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (3, 'kaushik', 23, 'Kota', 2000.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (4, 'Chaitali', 25, 'Mumbai', 6500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (5, 'Hardik', 27, 'Bhopal', 8500.00 );");
        statement.execute("INSERT INTO CUSTOMERS (ID,NAME,AGE,ADDRESS,SALARY) VALUES (6, 'Komal', 22, 'MP', 4500.00 );");

        PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE ID=?");
        pstmt.setLong(1, 3);
        ResultSet resultSet = pstmt.executeQuery();


        while (resultSet.next()) {
            System.out.println("");
            System.out.println("ID : " + resultSet.getLong("ID"));
            System.out.println("NAME : " + resultSet.getString("NAME"));
            System.out.println("AGE : " + resultSet.getInt("AGE"));
        }
    }

    private DataSource getH2DataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }
}