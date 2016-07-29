package net.chandol.logjdbc.jdbcproxy;

import net.chandol.logjdbc.logging.LogContext;
import net.chandol.logjdbc.logging.collector.parameter.ParameterCollector;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

import static net.chandol.logjdbc.logging.collector.parameter.ParameterType.*;

public class ProxyPreparedStatement extends ProxyStatement implements PreparedStatement {
    protected PreparedStatement _pstmt;
    private ParameterCollector paramCollector;

    public ProxyPreparedStatement(LogContext context, PreparedStatement preparedStatement) {
        super(context, preparedStatement);
        this._pstmt = preparedStatement;
        this.paramCollector = context.initParameterCollector();
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        ResultSet resultSet = _pstmt.executeQuery();
        return new ProxyResultSet(context, resultSet);
    }

    @Override
    public int executeUpdate() throws SQLException {
        context.printLog();
        return _pstmt.executeUpdate();
    }

    @Override
    public boolean execute() throws SQLException {
        context.printLog();
        return _pstmt.execute();
    }

    @Override
    public void setNull(int index, int sqlType) throws SQLException {
        paramCollector.add(index, _Null, null);
        _pstmt.setNull(index, sqlType);
    }

    @Override
    public void setBoolean(int index, boolean x) throws SQLException {
        paramCollector.add(index, _Boolean, x);
        _pstmt.setBoolean(index, x);
    }

    @Override
    public void setByte(int index, byte x) throws SQLException {
        paramCollector.add(index, _Byte, x);
        _pstmt.setByte(index, x);
    }

    @Override
    public void setShort(int index, short x) throws SQLException {
        paramCollector.add(index, _Short, x);
        _pstmt.setShort(index, x);
    }

    @Override
    public void setInt(int index, int x) throws SQLException {
        paramCollector.add(index, _Int, x);
        _pstmt.setInt(index, x);
    }

    @Override
    public void setLong(int index, long x) throws SQLException {
        paramCollector.add(index, _Long, x);
        _pstmt.setLong(index, x);
    }

    @Override
    public void setFloat(int index, float x) throws SQLException {
        paramCollector.add(index, _Float, x);
        _pstmt.setFloat(index, x);
    }

    @Override
    public void setDouble(int index, double x) throws SQLException {
        paramCollector.add(index, _Double, x);
        _pstmt.setDouble(index, x);
    }

    @Override
    public void setBigDecimal(int index, BigDecimal x) throws SQLException {
        paramCollector.add(index, _BigDecimal, x);
        _pstmt.setBigDecimal(index, x);
    }

    @Override
    public void setString(int index, String x) throws SQLException {
        paramCollector.add(index, _String, x);
        _pstmt.setString(index, x);
    }

    @Override
    public void setBytes(int index, byte[] x) throws SQLException {
        paramCollector.add(index, _Bytes, "<byte[]>");
        _pstmt.setBytes(index, x);
    }

    @Override
    public void setDate(int index, Date x) throws SQLException {
        paramCollector.add(index, _Date, x);
        _pstmt.setDate(index, x);
    }

    @Override
    public void setTime(int index, Time x) throws SQLException {
        paramCollector.add(index, _Time, x);
        _pstmt.setTime(index, x);
    }

    @Override
    public void setTimestamp(int index, Timestamp x) throws SQLException {
        paramCollector.add(index, _Timestamp, x);
        _pstmt.setTimestamp(index, x);
    }

    @Override
    public void setAsciiStream(int index, InputStream x, int length) throws SQLException {
        paramCollector.add(index, _AsciiStream, "<AsciiStream length : " + length + ">");
        _pstmt.setAsciiStream(index, x, length);
    }

    @Override
    public void setUnicodeStream(int index, InputStream x, int length) throws SQLException {
        paramCollector.add(index, UnicodeStream, "<UnicodeStream length : " + length + ">");
        _pstmt.setUnicodeStream(index, x, length);
    }

    @Override
    public void setBinaryStream(int index, InputStream x, int length) throws SQLException {
        paramCollector.add(index, _BinaryStream, "<BinaryStream length : " + length + ">");
        _pstmt.setBinaryStream(index, x, length);
    }

    @Override
    public void setObject(int index, Object x, int targetSqlType) throws SQLException {
        paramCollector.add(index, _Object, "<Object>");
        _pstmt.setObject(index, x, targetSqlType);
    }

    @Override
    public void setObject(int index, Object x) throws SQLException {
        paramCollector.add(index, _Object, "<Object>");
        _pstmt.setObject(index, x);
    }

    @Override
    public void setCharacterStream(int index, Reader reader, int length) throws SQLException {
        paramCollector.add(index, _CharacterStream, "<CharacterStream length : " + length + ">");
        _pstmt.setCharacterStream(index, reader, length);
    }

    @Override
    public void setRef(int index, Ref x) throws SQLException {
        paramCollector.add(index, _Ref, x);
        _pstmt.setRef(index, x);
    }

    @Override
    public void setBlob(int index, Blob x) throws SQLException {
        paramCollector.add(index, _Blob, "<Blob of size " + x.length() + ">");
        _pstmt.setBlob(index, x);
    }

    @Override
    public void setClob(int index, Clob x) throws SQLException {
        paramCollector.add(index, _Clob, "<Clob of size " + x.length() + ">");
        _pstmt.setClob(index, x);
    }

    @Override
    public void setArray(int index, Array x) throws SQLException {
        paramCollector.add(index, _Array, "<Array>");
        _pstmt.setArray(index, x);
    }

    @Override
    public void setDate(int index, Date x, Calendar cal) throws SQLException {
        paramCollector.add(index, _Date, x);
        _pstmt.setDate(index, x, cal);
    }

    @Override
    public void setTime(int index, Time x, Calendar cal) throws SQLException {
        paramCollector.add(index, _Time, x);
        _pstmt.setTime(index, x, cal);
    }

    @Override
    public void setTimestamp(int index, Timestamp x, Calendar cal) throws SQLException {
        paramCollector.add(index, _Timestamp, x);
        _pstmt.setTimestamp(index, x, cal);
    }

    @Override
    public void setNull(int index, int sqlType, String typeName) throws SQLException {
        paramCollector.add(index, _Null, null);
        _pstmt.setNull(index, sqlType, typeName);
    }

    @Override
    public void setURL(int index, URL x) throws SQLException {
        paramCollector.add(index, _URL, x);
        _pstmt.setURL(index, x);
    }

    @Override
    public void setRowId(int index, RowId x) throws SQLException {
        paramCollector.add(index, _RowId, x);
        _pstmt.setRowId(index, x);
    }

    @Override
    public void setNString(int index, String value) throws SQLException {
        paramCollector.add(index, _NString, value);
        _pstmt.setNString(index, value);
    }

    @Override
    public void setNCharacterStream(int index, Reader value, long length) throws SQLException {
        paramCollector.add(index, _NCharacterStream, "<Reader of length " + length + ">");
        _pstmt.setNCharacterStream(index, value, length);
    }

    @Override
    public void setNClob(int index, NClob value) throws SQLException {
        paramCollector.add(index, _NClob, "<NClob>");
        _pstmt.setNClob(index, value);
    }

    @Override
    public void setClob(int index, Reader reader, long length) throws SQLException {
        paramCollector.add(index, _Clob, "<Reader>");
        _pstmt.setClob(index, reader, length);
    }

    @Override
    public void setBlob(int index, InputStream inputStream, long length) throws SQLException {
        paramCollector.add(index, _Blob, "<InputStream length : " + length + ">");
        _pstmt.setBlob(index, inputStream, length);
    }

    @Override
    public void setNClob(int index, Reader reader, long length) throws SQLException {
        paramCollector.add(index, _NClob, "<Reader length : " + length + ">");
        _pstmt.setNClob(index, reader, length);
    }

    @Override
    public void setSQLXML(int index, SQLXML xmlObject) throws SQLException {
        paramCollector.add(index, _SQLXML, xmlObject);
        _pstmt.setSQLXML(index, xmlObject);
    }

    @Override
    public void setObject(int index, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        paramCollector.add(index, _Object, "<Object>");
        _pstmt.setObject(index, x, targetSqlType, scaleOrLength);
    }

    @Override
    public void setAsciiStream(int index, InputStream x, long length) throws SQLException {
        paramCollector.add(index, _AsciiStream, "<InputStream length : " + length + ">");
        _pstmt.setAsciiStream(index, x, length);
    }

    @Override
    public void setBinaryStream(int index, InputStream x, long length) throws SQLException {
        paramCollector.add(index, _BinaryStream, "<InputStream length : " + length + ">");
        _pstmt.setBinaryStream(index, x, length);
    }

    @Override
    public void setCharacterStream(int index, Reader reader, long length) throws SQLException {
        paramCollector.add(index, _CharacterStream, "<Reader length : " + length + ">");
        _pstmt.setCharacterStream(index, reader, length);
    }

    @Override
    public void setAsciiStream(int index, InputStream x) throws SQLException {
        paramCollector.add(index, _AsciiStream, "<InputStream>");
        _pstmt.setAsciiStream(index, x);
    }

    @Override
    public void setBinaryStream(int index, InputStream x) throws SQLException {
        paramCollector.add(index, _BinaryStream, "<InputStream>");
        _pstmt.setBinaryStream(index, x);
    }

    @Override
    public void setCharacterStream(int index, Reader reader) throws SQLException {
        paramCollector.add(index, _CharacterStream, "<Reader>");
        _pstmt.setCharacterStream(index, reader);
    }

    @Override
    public void setNCharacterStream(int index, Reader value) throws SQLException {
        paramCollector.add(index, _NCharacterStream, "<Reader>");
        _pstmt.setNCharacterStream(index, value);
    }

    @Override
    public void setClob(int index, Reader reader) throws SQLException {
        paramCollector.add(index, _Clob, "<Reader>");
        _pstmt.setClob(index, reader);
    }

    @Override
    public void setBlob(int index, InputStream inputStream) throws SQLException {
        paramCollector.add(index, _Blob, "<InputStream>");
        _pstmt.setBlob(index, inputStream);
    }

    @Override
    public void setNClob(int index, Reader reader) throws SQLException {
        paramCollector.add(index, _NClob, "<Reader>");
        _pstmt.setNClob(index, reader);
    }

    @Override
    public void clearParameters() throws SQLException {
        _pstmt.clearParameters();
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return _pstmt.getMetaData();
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return _pstmt.getParameterMetaData();
    }

    @Override
    public void addBatch() throws SQLException {
        _pstmt.addBatch();
    }

}
