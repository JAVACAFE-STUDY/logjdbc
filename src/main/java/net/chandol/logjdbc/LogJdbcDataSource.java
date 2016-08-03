package net.chandol.logjdbc;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.jdbcproxy.ProxyConnection;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * 기존의 Datasource를 감싸주는 프록시로써의 역할을 한다.
 * 해당 프록시는 SQL문, parameter를 캐치하여 로그를 만들어준다.
 */
public class LogJdbcDataSource implements DataSource {
    private DataSource _datasource;
    private LogJdbcConfig config;

    public LogJdbcDataSource(DataSource dataSource) {
        this._datasource = dataSource;
        this.config = new LogJdbcConfig();
    }

    public LogJdbcDataSource(DataSource dataSource, LogJdbcConfig config) {
        this._datasource = dataSource;
        this.config = config;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (databaseTypeNotExist())
            config.setDatabaseTypeBaseOnDatasource(_datasource);

        return new ProxyConnection(config, this._datasource.getConnection());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if (databaseTypeNotExist())
            config.setDatabaseTypeBaseOnDatasource(_datasource);

        return new ProxyConnection(config, this._datasource.getConnection(username, password));
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this._datasource.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this._datasource.isWrapperFor(iface);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return this._datasource.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        this._datasource.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        this._datasource.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return this._datasource.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return this._datasource.getParentLogger();
    }

    private boolean databaseTypeNotExist() {
        return config.getType() == null;
    }
}