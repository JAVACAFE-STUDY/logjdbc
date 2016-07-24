package net.chandol.datasource.sql.resultset;

import net.chandol.datasource.except.LoggableDataSourceException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static net.chandol.datasource.sql.resultset.ResultSetUtil.getColumnCount;

public class ResultSetCollector {
    private ResultSetData datas;
    private int columnCount;

    public ResultSetCollector(ResultSetMetaData metaData) {
        this.datas = new ResultSetData(metaData);
        this.columnCount = getColumnCount(metaData);
    }

    public void collectCurrentCursorResultSetData(ResultSet resultSet) {
        try {
            Object[] values = new Object[columnCount];
            for (int idx = 0; idx < columnCount; idx++)
                values[idx] = resultSet.getObject(idx + 1);

            datas.addRow(values);
        } catch (SQLException e) {
            throw new LoggableDataSourceException(e);
        }
    }

    public ResultSetData getResultSetData() {
        return this.datas;
    }

}
