package net.chandol.datasource;

import net.chandol.datasource.jdbcproxy.ProxyConnection;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import static net.chandol.datasource.LoggableDataSourceConfig.autoconfig;

public class LoggableDataSource implements DataSource {
    private DataSource _datasource;
    private LoggableDataSourceConfig config;

    public LoggableDataSource(DataSource dataSource) {
        this._datasource = dataSource;
    }

    public LoggableDataSource(DataSource dataSource, LoggableDataSourceConfig config) {
        this._datasource = dataSource;
        this.config = config;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (config == null){
            config = autoconfig(_datasource);
        }

        return new ProxyConnection(config, this._datasource.getConnection());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if (config == null){
            config = autoconfig(_datasource);
        }

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
}