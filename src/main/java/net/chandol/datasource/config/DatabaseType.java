package net.chandol.datasource.config;

import net.chandol.datasource.except.LoggableDataSourceException;
import net.chandol.datasource.sql.parameter.converter.BaseParameterConverter;
import net.chandol.datasource.sql.parameter.converter.MysqlParameterConverter;
import net.chandol.datasource.sql.parameter.converter.OracleParameterConverter;
import net.chandol.datasource.sql.parameter.converter.ParameterConverter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static net.chandol.datasource.config.DatabaseInstanceHolder.getParameterConverterInstance;
import static net.chandol.datasource.config.DatabaseTypeUtil.findDatabaseType;
import static net.chandol.datasource.config.DatabaseTypeUtil.getDatabaseName;

/**
 * 데이터베이스가 정의된다.
 */
public enum DatabaseType {
    H2(BaseParameterConverter.class, "H2"),
    MYSQL(MysqlParameterConverter.class, "MySql", "MariaDB"),
    ORACLE(OracleParameterConverter.class, "Oracle"),
    UNKNOWN(BaseParameterConverter.class);

    /* enum필드 */
    String[] matchNames; // 데이터베이스 이름과 비교할 문자열
    Class<? extends ParameterConverter> converterClass; // enum은 컨버터 클래스를 가진다.

    /* 생성자 */
    DatabaseType(Class<? extends ParameterConverter> converterClass, String... matchNames) {
        this.converterClass = converterClass;
        this.matchNames = matchNames;
    }

    // 컨버터 인스턴스를 반환한다.
    public ParameterConverter getParameterConverter() {
        return getParameterConverterInstance(this);
    }

    // 현재 데이터소스와 매칭되는 데이터베이스 타입을 찾는다.
    public static DatabaseType find(DataSource datasource) {
        String databaseName = getDatabaseName(datasource);
        return findDatabaseType(databaseName);
    }
}


// enum클래스에서 사용되는 유틸성 함수들은 분리한다.
final class DatabaseTypeUtil {

    // datasource에서 데이터베이스명을 가져온다.
    static String getDatabaseName(DataSource datasource) {
        try (Connection connection = datasource.getConnection()) {
            return connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw new LoggableDataSourceException("Error!! fail to find database type", e);
        }
    }

    // DB명을 기준으로 데이터베이스 타입을 찾는다.
    static DatabaseType findDatabaseType(String databaseName) {
        DatabaseType[] types = DatabaseType.values();
        databaseName = databaseName.toLowerCase();

        for (DatabaseType type : types) {
            for (String matchName : type.matchNames) {
                if (databaseName.startsWith(matchName.toLowerCase()))
                    return type;
            }
        }

        // 찾는 타입이 없을 경우에는 UNKNOWN을 반환한다.
        return DatabaseType.UNKNOWN;
    }
}

/**
 * 데이터베이스 컨버터 인스턴스를 관리하는 역할수행
 * LazyLoding을 적용하면서 enum의 복잡도가 올라감.
 * 클래스 분리를 통해서 복잡도를 낮추기 위함임
 */
final class DatabaseInstanceHolder {
    private final static Map<DatabaseType, ParameterConverter> instanceHolder = new ConcurrentHashMap<>();

    // 파라미터 컨버터 인스턴스를 생성 및 보관한다.
    // 확률은 낮지만 싱글톤이 여러개 생성되는것을 방지한다.
    // 데이터 소스는 한꺼번에 생성하지 않으므로 synchronized를 사용하더라도 문제없다고 판단됨.
    static synchronized ParameterConverter getParameterConverterInstance(DatabaseType type) {
        if (!instanceHolder.containsKey(type)) {
            try {
                ParameterConverter converter = type.converterClass.newInstance();
                instanceHolder.put(type, converter);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new LoggableDataSourceException(e);
            }
        }

        return instanceHolder.get(type);
    }
}
