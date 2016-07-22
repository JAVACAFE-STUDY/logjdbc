package net.chandol.datasource;

import net.chandol.datasource.sql.formatter.DefaultSqlFormatter;
import net.chandol.datasource.sql.formatter.SqlFormatter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;

import javax.sql.DataSource;

public class LoggableDataSourceConfig {
    private DatabaseType type;
    private ParameterConverter converter;
    private SqlFormatter formatter;

    // 각 항목들을 초기화시킵니다.
    public LoggableDataSourceConfig(DatabaseType type, SqlFormatter formatter) {
        this.type = type;
        this.converter = type.getParameterConverterInstance();
        this.formatter = formatter;
    }

    public LoggableDataSourceConfig(DatabaseType type) {
        this(type, new DefaultSqlFormatter());
    }

    public DatabaseType getType() {
        return type;
    }

    public ParameterConverter getConverter() {
        return converter;
    }

    public SqlFormatter getFormatter() {
        return formatter;
    }


    static LoggableDataSourceConfig autoconfig(DataSource datasource) {
        DatabaseType type = DatabaseType.find(datasource);
        SqlFormatter formatter = new DefaultSqlFormatter();

        return new LoggableDataSourceConfig(type, formatter);
    }
}
