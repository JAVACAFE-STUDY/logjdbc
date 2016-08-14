package net.chandol.logjdbc.logging.printer.resultset;

import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetData;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ResultSetTablePrinterTest {
    @Test
    public void 특정갯수의ResultSet생성확인() throws Exception {
        // given
        ResultSetMetaData metaData = getMockResultSetMetaData();
        ResultSetData resultSetData = getMockResultSetData(metaData);
        ResultSetTablePrinter printer = ResultSetTablePrinter.getInstance();

        // when
        String resultSetTable = printer.getResultSetTable(resultSetData, 10);

        // then
        System.out.println(resultSetTable);
        List<String> splittedTable = Arrays.asList(resultSetTable.split("\n"));
        assertThat(splittedTable.size(), is(14));
        assertThat(splittedTable.get(12), is("| 10 | test10 |"));
    }

    private ResultSetData getMockResultSetData(ResultSetMetaData metaData) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ResultSetCollector rsCollector = new ResultSetCollector(metaData);
        ResultSetData resultSetData = rsCollector.getResultSetData();

        Method addMethod = ResultSetData.class.getDeclaredMethod("addRow", Object[].class);
        addMethod.setAccessible(true);
        for (int idx = 1; idx <= 20; idx++) {
            addMethod.invoke(resultSetData, new Object[] {new Object[] {idx, "test" + idx}});
        }
        return resultSetData;
    }

    private ResultSetMetaData getMockResultSetMetaData() throws SQLException {
        ResultSetMetaData metaData = Mockito.mock(ResultSetMetaData.class);
        Mockito.when(metaData.getColumnCount()).thenReturn(2);
        Mockito.when(metaData.getColumnLabel(1)).thenReturn("id");
        Mockito.when(metaData.getColumnTypeName(2)).thenReturn("INTEGER");
        Mockito.when(metaData.getColumnLabel(2)).thenReturn("name");
        Mockito.when(metaData.getColumnTypeName(2)).thenReturn("STRING");
        return metaData;
    }

}