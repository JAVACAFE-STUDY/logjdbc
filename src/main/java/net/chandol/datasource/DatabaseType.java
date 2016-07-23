package net.chandol.datasource;

import net.chandol.datasource.except.LoggableDataSourceException;
import net.chandol.datasource.sql.parameter.converter.BaseParameterConverter;
import net.chandol.datasource.sql.parameter.converter.MysqlParameterConverter;
import net.chandol.datasource.sql.parameter.converter.NeedImplementParameterConverter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static net.chandol.datasource.DatabaseTypeUtil.getDatabaseName;
import static net.chandol.datasource.DatabaseTypeUtil.isMatchDatabaseName;

/**
 * 지원하는 데이터베이스 타입
 */
public enum DatabaseType {
    MYSQL(MysqlParameterConverter.class),
    MARIADB(MysqlParameterConverter.class),
    H2(BaseParameterConverter.class),
    ORACLE(NeedImplementParameterConverter.class),
    POSTGRESQL(NeedImplementParameterConverter.class),
    UNKNOWN(BaseParameterConverter.class);

    /* enum필드 */

    // 컨버터 클래스가 들어와요!
    private Class<? extends ParameterConverter> converterClass;
    private ParameterConverter converter;

    /* 생성자 */
    // Lazy로딩을 적용. 실제 인스턴스는 getParameterConverter가 호출될 때 생성한다.
    DatabaseType(Class<? extends ParameterConverter> converterClass) {
        this.converterClass = converterClass;
    }

    // 컨버터를 반환한다. converter는 하나의 인스턴스만을 관리한다.
    // TODO 동시성문제 발생가능성 있음
    public ParameterConverter getParameterConverter() {
        if (this.converter == null)
            this.converter = getParameterConverterInstance();

        return this.converter;
    }

    private ParameterConverter getParameterConverterInstance() {
        ParameterConverter converter;
        try {
            converter = converterClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new LoggableDataSourceException(e);
        }
        return converter;
    }

    // FIXME 데이터베이스명에 따라서 벤더를 선택할 수 있도록 만들 것.
    // FIXME 생성자로 매칭문자열을 받을 것.
    public static DatabaseType find(DataSource datasource) {
        String name = getDatabaseName(datasource);

        if (isMatchDatabaseName(name, "H2"))
            return H2;
        else if (isMatchDatabaseName(name, "MySql"))
            return MYSQL;
        else
            return UNKNOWN;
    }
}


//enum의 복잡도를 낮추기 위해서 아래와 같이 분리한다.
final class DatabaseTypeUtil {

    // datasource에서 데이터베이스명을 가져온다.
    static String getDatabaseName(DataSource datasource) {
        try (Connection connection = datasource.getConnection()) {
            return connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw new LoggableDataSourceException("Error!! fail to find database type", e);
        }
    }

    // DB명이 맞는지를 검사해본다.
    static boolean isMatchDatabaseName(String databaseName, String typeName) {
        return databaseName.toLowerCase()
                .startsWith(typeName.toLowerCase());
    }
}
