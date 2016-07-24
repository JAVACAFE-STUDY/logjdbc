package net.chandol.datasource.sql.resultset;

import net.chandol.datasource.except.LoggableDataSourceException;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

final class ResultSetUtil {
    static List<Column> createColumns(ResultSetMetaData metaData) {
        try {
            List<Column> columns = new ArrayList<>();
            int count = getColumnCount(metaData);

            for (int idx = 1; idx <= count; idx++) {
                String label = metaData.getColumnLabel(idx);
                String type = metaData.getColumnTypeName(idx);
                columns.add(new Column(label, type));
            }

            return columns;

        } catch (SQLException e) {
            throw new LoggableDataSourceException(e);
        }
    }

    static int getColumnCount(ResultSetMetaData metaData) {
        try {
            return metaData.getColumnCount();
        } catch (SQLException e) {
            throw new LoggableDataSourceException(e);
        }
    }
}
