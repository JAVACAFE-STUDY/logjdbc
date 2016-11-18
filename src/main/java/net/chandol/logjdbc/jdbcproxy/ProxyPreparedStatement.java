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
    private PreparedStatement _pstmt;
    private ParameterCollector paramCollector;

    public ProxyPreparedStatement(LogContext context, PreparedStatement preparedStatement) {
        super(context, preparedStatement);
        this._pstmt = preparedStatement;
        this.paramCollector = context.initParameterCollector();
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        try {
            ResultSet resultSet = _pstmt.executeQuery();
            return new ProxyResultSet(context, resultSet);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public int executeUpdate() throws SQLException {
        try {
            return _pstmt.executeUpdate();
        } finally {
            context.printLog();
        }
    }

    @Override
    public boolean execute() throws SQLException {
        try {
            return _pstmt.execute();
        } finally {
            context.printLog();
        }
    }

    @Override
    public void setNull(int index, int sqlType) throws SQLException {
        try {
            _pstmt.setNull(index, sqlType);
            paramCollector.add(index, _Null, null);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBoolean(int index, boolean x) throws SQLException {
        try {
            _pstmt.setBoolean(index, x);
            paramCollector.add(index, _Boolean, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setByte(int index, byte x) throws SQLException {
        try {
            _pstmt.setByte(index, x);
            paramCollector.add(index, _Byte, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setShort(int index, short x) throws SQLException {
        try {
            _pstmt.setShort(index, x);
            paramCollector.add(index, _Short, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setInt(int index, int x) throws SQLException {
        try {
            _pstmt.setInt(index, x);
            paramCollector.add(index, _Int, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setLong(int index, long x) throws SQLException {
        try {
            _pstmt.setLong(index, x);
            paramCollector.add(index, _Long, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setFloat(int index, float x) throws SQLException {
        try {
            _pstmt.setFloat(index, x);
            paramCollector.add(index, _Float, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setDouble(int index, double x) throws SQLException {
        try {
            _pstmt.setDouble(index, x);
            paramCollector.add(index, _Double, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBigDecimal(int index, BigDecimal x) throws SQLException {
        try {
            _pstmt.setBigDecimal(index, x);
            paramCollector.add(index, _BigDecimal, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setString(int index, String x) throws SQLException {
        try {
            _pstmt.setString(index, x);
            paramCollector.add(index, _String, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBytes(int index, byte[] x) throws SQLException {
        try {
            _pstmt.setBytes(index, x);
            paramCollector.add(index, _Bytes, "<byte[]>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setDate(int index, Date x) throws SQLException {
        try {
            _pstmt.setDate(index, x);
            paramCollector.add(index, _Date, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setTime(int index, Time x) throws SQLException {
        try {
            _pstmt.setTime(index, x);
            paramCollector.add(index, _Time, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setTimestamp(int index, Timestamp x) throws SQLException {
        try {
            _pstmt.setTimestamp(index, x);
            paramCollector.add(index, _Timestamp, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setAsciiStream(int index, InputStream x, int length) throws SQLException {

        try {
            _pstmt.setAsciiStream(index, x, length);
            paramCollector.add(index, _AsciiStream, "<AsciiStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setUnicodeStream(int index, InputStream x, int length) throws SQLException {
        try {
            _pstmt.setUnicodeStream(index, x, length);
            paramCollector.add(index, UnicodeStream, "<UnicodeStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBinaryStream(int index, InputStream x, int length) throws SQLException {
        try {
            _pstmt.setBinaryStream(index, x, length);
            paramCollector.add(index, _BinaryStream, "<BinaryStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setObject(int index, Object x, int targetSqlType) throws SQLException {
        try {
            _pstmt.setObject(index, x, targetSqlType);

            if (isCharArr(x))
                paramCollector.add(index, _Object, "<Object>");
            else
                paramCollector.add(index, _String, x.toString());
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setObject(int index, Object x) throws SQLException {
        try {
            _pstmt.setObject(index, x);

            if (isCharArr(x))
                paramCollector.add(index, _Object, "<Object>");
            else
                paramCollector.add(index, _String, x.toString());
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setCharacterStream(int index, Reader reader, int length) throws SQLException {
        try {
            _pstmt.setCharacterStream(index, reader, length);
            paramCollector.add(index, _CharacterStream, "<CharacterStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setRef(int index, Ref x) throws SQLException {
        try {
            _pstmt.setRef(index, x);
            paramCollector.add(index, _Ref, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBlob(int index, Blob x) throws SQLException {
        try {
            _pstmt.setBlob(index, x);
            paramCollector.add(index, _Blob, "<Blob of size " + x.length() + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setClob(int index, Clob x) throws SQLException {
        try {
            _pstmt.setClob(index, x);
            paramCollector.add(index, _Clob, "<Clob of size " + x.length() + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setArray(int index, Array x) throws SQLException {
        try {
            _pstmt.setArray(index, x);
            paramCollector.add(index, _Array, "<Array>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setDate(int index, Date x, Calendar cal) throws SQLException {
        try {
            _pstmt.setDate(index, x, cal);
            paramCollector.add(index, _Date, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setTime(int index, Time x, Calendar cal) throws SQLException {
        try {
            _pstmt.setTime(index, x, cal);
            paramCollector.add(index, _Time, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setTimestamp(int index, Timestamp x, Calendar cal) throws SQLException {
        try {
            _pstmt.setTimestamp(index, x, cal);
            paramCollector.add(index, _Timestamp, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNull(int index, int sqlType, String typeName) throws SQLException {
        try {
            _pstmt.setNull(index, sqlType, typeName);
            paramCollector.add(index, _Null, null);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setURL(int index, URL x) throws SQLException {
        try {
            _pstmt.setURL(index, x);
            paramCollector.add(index, _URL, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setRowId(int index, RowId x) throws SQLException {
        try {
            _pstmt.setRowId(index, x);
            paramCollector.add(index, _RowId, x);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNString(int index, String value) throws SQLException {
        try {
            _pstmt.setNString(index, value);
            paramCollector.add(index, _NString, value);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNCharacterStream(int index, Reader value, long length) throws SQLException {
        try {
            _pstmt.setNCharacterStream(index, value, length);
            paramCollector.add(index, _NCharacterStream, "<Reader of length " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNClob(int index, NClob value) throws SQLException {
        try {
            _pstmt.setNClob(index, value);
            paramCollector.add(index, _NClob, "<NClob>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setClob(int index, Reader reader, long length) throws SQLException {
        try {
            _pstmt.setClob(index, reader, length);
            paramCollector.add(index, _Clob, "<Reader>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBlob(int index, InputStream inputStream, long length) throws SQLException {
        try {
            _pstmt.setBlob(index, inputStream, length);
            paramCollector.add(index, _Blob, "<InputStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNClob(int index, Reader reader, long length) throws SQLException {
        try {
            _pstmt.setNClob(index, reader, length);
            paramCollector.add(index, _NClob, "<Reader length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setSQLXML(int index, SQLXML xmlObject) throws SQLException {
        try {
            _pstmt.setSQLXML(index, xmlObject);
            paramCollector.add(index, _SQLXML, xmlObject);
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setObject(int index, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        try {
            _pstmt.setObject(index, x, targetSqlType, scaleOrLength);
            paramCollector.add(index, _Object, "<Object>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setAsciiStream(int index, InputStream x, long length) throws SQLException {
        try {
            _pstmt.setAsciiStream(index, x, length);
            paramCollector.add(index, _AsciiStream, "<InputStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBinaryStream(int index, InputStream x, long length) throws SQLException {
        try {
            _pstmt.setBinaryStream(index, x, length);
            paramCollector.add(index, _BinaryStream, "<InputStream length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setCharacterStream(int index, Reader reader, long length) throws SQLException {
        try {
            _pstmt.setCharacterStream(index, reader, length);
            paramCollector.add(index, _CharacterStream, "<Reader length : " + length + ">");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setAsciiStream(int index, InputStream x) throws SQLException {
        try {
            _pstmt.setAsciiStream(index, x);
            paramCollector.add(index, _AsciiStream, "<InputStream>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBinaryStream(int index, InputStream x) throws SQLException {
        try {
            _pstmt.setBinaryStream(index, x);
            paramCollector.add(index, _BinaryStream, "<InputStream>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setCharacterStream(int index, Reader reader) throws SQLException {
        try {
            _pstmt.setCharacterStream(index, reader);
            paramCollector.add(index, _CharacterStream, "<Reader>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNCharacterStream(int index, Reader value) throws SQLException {
        try {
            _pstmt.setNCharacterStream(index, value);
            paramCollector.add(index, _NCharacterStream, "<Reader>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setClob(int index, Reader reader) throws SQLException {
        try {
            _pstmt.setClob(index, reader);
            paramCollector.add(index, _Clob, "<Reader>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setBlob(int index, InputStream inputStream) throws SQLException {
        try {
            _pstmt.setBlob(index, inputStream);
            paramCollector.add(index, _Blob, "<InputStream>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
    }

    @Override
    public void setNClob(int index, Reader reader) throws SQLException {
        try {
            _pstmt.setNClob(index, reader);
            paramCollector.add(index, _NClob, "<Reader>");
        } catch (SQLException e) {
            context.printLog();
            throw e;
        }
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


    private static boolean isCharArr(Object x) {
        return x.getClass().isAssignableFrom(char[].class);
    }

}
