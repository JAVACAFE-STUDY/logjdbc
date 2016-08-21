package net.chandol.logjdbc.logging.printer.resultset;

import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.LogContext;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetData;
import net.chandol.logjdbc.util.AsciiTable4j;

import java.util.Arrays;
import java.util.List;

public class ResultSetTablePrinter implements ResultSetPrinter {
    private static ResultSetTablePrinter instance;

    public static ResultSetTablePrinter getInstance() {
        if (instance == null)
            instance = new ResultSetTablePrinter();

        return instance;
    }

    private ResultSetTablePrinter() {}

    @Override
    public void printResultSet(LogContext context) {
        LogJdbcConfig config = context.getConfig();
        ResultSetData data = context.getResultSetCollector().getResultSetData();

        int resultSetMaxLength = config.getProperties().getResultsetMaxlength();
        int printResultSetSize = Math.min(resultSetMaxLength, data.getRowsSize());

        String resultSetTable = getResultSetTable(data, printResultSetSize);

        context.getHelper().getLogger("resultset").debug("\n" + resultSetTable);
    }

    String getResultSetTable(ResultSetData data, int printResultSetSize) {
        AsciiTable4j table = new AsciiTable4j();

        table.addRow(data.getColumns());

        List<String[]> rowValues = data.getRows();
        for (int idx = 0; idx < printResultSetSize; idx++)
            table.addRow(Arrays.asList(rowValues.get(idx)));

        return table.renderTable();
    }
}
