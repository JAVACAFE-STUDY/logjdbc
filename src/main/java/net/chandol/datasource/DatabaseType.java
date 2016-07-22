package net.chandol.datasource;

import net.chandol.datasource.except.LoggableDataSourceException;
import net.chandol.datasource.sql.parameter.converter.CommonParameterConverter;
import net.chandol.datasource.sql.parameter.converter.MysqlParameterConverter;
import net.chandol.datasource.sql.parameter.converter.NeedImplementParameterConverter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public enum DatabaseType {
    MYSQL(MysqlParameterConverter.class),
    H2(CommonParameterConverter.class),
    ORACLE(NeedImplementParameterConverter.class),
    UNKNOWN(NeedImplementParameterConverter.class);

    // 컨버터 클래스가 들어와요!
    Class<? extends ParameterConverter> converterClass;

    DatabaseType(Class<? extends ParameterConverter> converterClass) {
        this.converterClass = converterClass;
    }

    // 컨버터를 반환합니다.
    public ParameterConverter getParameterConverterInstance() {
        try {
            return converterClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new LoggableDataSourceException(e);
        }
    }

    public static DatabaseType find(DataSource datasource) {
        String databaseName = getDatabaseName(datasource);

        if (databaseName.equals("H2"))
            return H2;
        else
            return MYSQL;
    }

    private static String getDatabaseName(DataSource datasource) {
        try (Connection connection = datasource.getConnection()) {
            return connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw new LoggableDataSourceException("Error!! fail to find database type", e);
        }
    }
}
