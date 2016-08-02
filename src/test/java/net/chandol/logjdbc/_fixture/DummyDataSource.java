package net.chandol.logjdbc._fixture;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DummyDataSource {
    public static DataSource getDummyH2DataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:test");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }
}
