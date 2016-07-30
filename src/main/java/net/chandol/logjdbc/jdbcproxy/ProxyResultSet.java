package net.chandol.logjdbc.jdbcproxy;

import net.chandol.logjdbc.except.LoggableDataSourceException;
import net.chandol.logjdbc.logging.LogContext;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class ProxyResultSet implements ResultSet {

    private ResultSet _resultSet;
    private ResultSetCollector rsCollector;
    private LogContext context;

    ProxyResultSet(LogContext context, ResultSet resultSet) {
        this.context = context;
        this._resultSet = resultSet;

        try {
            this.rsCollector = context.initResultSetCollector(getMetaData());
        } catch (SQLException e) {
            context.printLog();
            throw new LoggableDataSourceException(e);
        }
    }

    @Override
    public boolean next() throws SQLException {
        try {
            boolean hasNext = _resultSet.next();
            // 다음 값이 있는 경우 현재 값들을 collector에 추가합니다.
            if (hasNext)
                rsCollector.collectCurrentCursorResultSetData(this);

            return hasNext;
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            //끝까지 다 읽었을때에만 호출되게 해놓았음, 더 좋은 방법이 있을 경우 채택하도록 하자!
            _resultSet.close();
        } finally {
            context.printLog();
        }
    }

    @Override
    public boolean wasNull() throws SQLException {
        try {
            return _resultSet.wasNull();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        try {
            return _resultSet.getString(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        try {
            return _resultSet.getBoolean(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        try {
            return _resultSet.getByte(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        try {
            return _resultSet.getShort(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        try {
            return _resultSet.getInt(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        try {
            return _resultSet.getLong(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        try {
            return _resultSet.getFloat(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        try {
            return _resultSet.getDouble(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        try {
            return _resultSet.getBigDecimal(columnIndex, scale);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        try {
            return _resultSet.getBytes(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        try {
            return _resultSet.getDate(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        try {
            return _resultSet.getTime(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        try {
            return _resultSet.getTimestamp(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        try {
            return _resultSet.getAsciiStream(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        try {
            return _resultSet.getUnicodeStream(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        try {
            return _resultSet.getBinaryStream(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        try {
            return _resultSet.getString(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        try {
            return _resultSet.getBoolean(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        try {
            return _resultSet.getByte(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        try {
            return _resultSet.getShort(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        try {
            return _resultSet.getInt(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        try {
            return _resultSet.getLong(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        try {
            return _resultSet.getFloat(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        try {
            return _resultSet.getDouble(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        try {
            return _resultSet.getBigDecimal(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        try {
            return _resultSet.getBytes(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        try {
            return _resultSet.getDate(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        try {
            return _resultSet.getTime(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        try {
            return _resultSet.getTimestamp(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        try {
            return _resultSet.getAsciiStream(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        try {
            return _resultSet.getUnicodeStream(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        try {
            return _resultSet.getBinaryStream(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        try {
            return _resultSet.getWarnings();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void clearWarnings() throws SQLException {
        try {
            _resultSet.clearWarnings();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public String getCursorName() throws SQLException {
        try {
            return _resultSet.getCursorName();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        try {
            return _resultSet.getMetaData();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        try {
            return _resultSet.getObject(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        try {
            return _resultSet.getObject(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        try {
            return _resultSet.findColumn(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        try {
            return _resultSet.getCharacterStream(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        try {
            return _resultSet.getCharacterStream(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        try {
            return _resultSet.getBigDecimal(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        try {
            return _resultSet.getBigDecimal(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        try {
            return _resultSet.isBeforeFirst();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean isAfterLast() throws SQLException {
        try {
            return _resultSet.isAfterLast();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean isFirst() throws SQLException {
        try {
            return _resultSet.isFirst();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean isLast() throws SQLException {
        try {
            return _resultSet.isLast();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void beforeFirst() throws SQLException {
        try {
            _resultSet.beforeFirst();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void afterLast() throws SQLException {
        try {
            _resultSet.afterLast();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean first() throws SQLException {
        try {
            return _resultSet.first();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean last() throws SQLException {
        try {
            return _resultSet.last();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getRow() throws SQLException {
        try {
            return _resultSet.getRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        try {
            return _resultSet.absolute(row);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean relative(int rows) throws SQLException {
        try {
            return _resultSet.relative(rows);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean previous() throws SQLException {
        try {
            return _resultSet.previous();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        try {
            _resultSet.setFetchDirection(direction);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getFetchDirection() throws SQLException {
        try {
            return _resultSet.getFetchDirection();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        try {
            _resultSet.setFetchSize(rows);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getFetchSize() throws SQLException {
        try {
            return _resultSet.getFetchSize();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getType() throws SQLException {
        try {
            return _resultSet.getType();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getConcurrency() throws SQLException {
        try {
            return _resultSet.getConcurrency();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        try {
            return _resultSet.rowUpdated();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean rowInserted() throws SQLException {
        try {
            return _resultSet.rowInserted();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        try {
            return _resultSet.rowDeleted();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        try {
            _resultSet.updateNull(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        try {
            _resultSet.updateBoolean(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        try {
            _resultSet.updateByte(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        try {
            _resultSet.updateShort(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        try {
            _resultSet.updateInt(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        try {
            _resultSet.updateLong(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        _resultSet.updateFloat(columnIndex, x);
    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {

        try {
            _resultSet.updateDouble(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        try {
            _resultSet.updateBigDecimal(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        try {
            _resultSet.updateString(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        try {
            _resultSet.updateBytes(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        try {
            _resultSet.updateDate(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        try {
            _resultSet.updateTime(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        try {
            _resultSet.updateTimestamp(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        try {
            _resultSet.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        try {
            _resultSet.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        try {
            _resultSet.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        try {
            _resultSet.updateObject(columnIndex, x, scaleOrLength);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        try {
            _resultSet.updateObject(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        try {
            _resultSet.updateNull(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        try {
            _resultSet.updateBoolean(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        try {
            _resultSet.updateByte(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        try {
            _resultSet.updateShort(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        try {
            _resultSet.updateInt(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        try {
            _resultSet.updateLong(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        try {
            _resultSet.updateFloat(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        try {
            _resultSet.updateDouble(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        try {
            _resultSet.updateBigDecimal(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        try {
            _resultSet.updateString(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        try {
            _resultSet.updateBytes(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        try {
            _resultSet.updateDate(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        try {
            _resultSet.updateTime(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        try {
            _resultSet.updateTimestamp(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        try {
            _resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        try {
            _resultSet.updateBinaryStream(columnLabel, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        try {
            _resultSet.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        try {
            _resultSet.updateObject(columnLabel, x, scaleOrLength);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        try {
            _resultSet.updateObject(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void insertRow() throws SQLException {
        try {
            _resultSet.insertRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateRow() throws SQLException {
        try {
            _resultSet.updateRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void deleteRow() throws SQLException {
        try {
            _resultSet.deleteRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void refreshRow() throws SQLException {
        try {
            _resultSet.refreshRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        try {
            _resultSet.cancelRowUpdates();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void moveToInsertRow() throws SQLException {
        try {
            _resultSet.moveToInsertRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        try {
            _resultSet.moveToCurrentRow();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }

    }

    @Override
    public Statement getStatement() throws SQLException {
        try {
            return _resultSet.getStatement();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        try {
            return _resultSet.getObject(columnIndex, map);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        try {
            return _resultSet.getRef(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        try {
            return _resultSet.getBlob(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        try {
            return _resultSet.getClob(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        try {
            return _resultSet.getArray(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        try {
            return _resultSet.getObject(columnLabel, map);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        try {
            return _resultSet.getRef(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        try {
            return _resultSet.getBlob(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        try {
            return _resultSet.getClob(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        try {
            return _resultSet.getArray(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        try {
            return _resultSet.getDate(columnIndex, cal);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        try {
            return _resultSet.getDate(columnLabel, cal);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        try {
            return _resultSet.getTime(columnIndex, cal);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        try {
            return _resultSet.getTime(columnLabel, cal);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        try {
            return _resultSet.getTimestamp(columnIndex, cal);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        try {
            return _resultSet.getTimestamp(columnLabel, cal);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        try {
            return _resultSet.getURL(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        try {
            return _resultSet.getURL(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        try {
            _resultSet.updateRef(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        try {
            _resultSet.updateRef(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        try {
            _resultSet.updateBlob(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        try {
            _resultSet.updateBlob(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        try {
            _resultSet.updateClob(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        try {
            _resultSet.updateClob(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        try {
            _resultSet.updateArray(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        try {
            _resultSet.updateArray(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        try {
            return _resultSet.getRowId(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        try {
            return _resultSet.getRowId(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        try {
            _resultSet.updateRowId(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        try {
            _resultSet.updateRowId(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int getHoldability() throws SQLException {
        try {
            return _resultSet.getHoldability();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean isClosed() throws SQLException {
        try {
            return _resultSet.isClosed();
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNString(int columnIndex, String nString) throws SQLException {
        try {
            _resultSet.updateNString(columnIndex, nString);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNString(String columnLabel, String nString) throws SQLException {
        try {
            _resultSet.updateNString(columnLabel, nString);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        try {
            _resultSet.updateNClob(columnIndex, nClob);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        try {
            _resultSet.updateNClob(columnLabel, nClob);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        try {
            return _resultSet.getNClob(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        try {
            return _resultSet.getNClob(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        try {
            return _resultSet.getSQLXML(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        try {
            return _resultSet.getSQLXML(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        try {
            _resultSet.updateSQLXML(columnIndex, xmlObject);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        try {
            _resultSet.updateSQLXML(columnLabel, xmlObject);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        try {
            return _resultSet.getNString(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        try {
            return _resultSet.getNString(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        try {
            return _resultSet.getNCharacterStream(columnIndex);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        try {
            return _resultSet.getNCharacterStream(columnLabel);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        try {
            _resultSet.updateNCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            _resultSet.updateNCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        try {
            _resultSet.updateAsciiStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        try {
            _resultSet.updateBinaryStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        try {
            _resultSet.updateCharacterStream(columnIndex, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        try {
            _resultSet.updateAsciiStream(columnLabel, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        try {
            _resultSet.updateBinaryStream(columnLabel, x, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            _resultSet.updateCharacterStream(columnLabel, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        try {
            _resultSet.updateBlob(columnIndex, inputStream, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {

        try {
            _resultSet.updateBlob(columnLabel, inputStream, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        try {
            _resultSet.updateClob(columnIndex, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            _resultSet.updateClob(columnLabel, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        try {
            _resultSet.updateNClob(columnIndex, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        try {
            _resultSet.updateNClob(columnLabel, reader, length);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        try {
            _resultSet.updateNCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        try {
            _resultSet.updateNCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        try {
            _resultSet.updateAsciiStream(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        try {
            _resultSet.updateBinaryStream(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        try {
            _resultSet.updateCharacterStream(columnIndex, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        try {
            _resultSet.updateAsciiStream(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        try {
            _resultSet.updateBinaryStream(columnLabel, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        try {
            _resultSet.updateCharacterStream(columnLabel, reader);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        try {
            _resultSet.updateBlob(columnIndex, inputStream);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        try {
            _resultSet.updateBlob(columnLabel, inputStream);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        try {
            _resultSet.updateClob(columnIndex, reader);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        try {
            _resultSet.updateClob(columnLabel, reader);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        try {
            _resultSet.updateNClob(columnIndex, reader);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        try {
            _resultSet.updateNClob(columnLabel, reader);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        try {
            return _resultSet.getObject(columnIndex, type);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        try {
            return _resultSet.getObject(columnLabel, type);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        try {
            return _resultSet.unwrap(iface);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        try {
            return _resultSet.isWrapperFor(iface);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }
}
