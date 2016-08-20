package net.chandol.logjdbc.logging.printer.resultset;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.LogContext;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ResultSetTablePrinter implements ResultSetPrinter {
    private static ResultSetTablePrinter instance;

    public static ResultSetTablePrinter getInstance() {
        if (instance == null)
            instance = new ResultSetTablePrinter();

        return instance;
    }

    private static final Logger rsLogger = LoggerFactory.getLogger("net.chandol.logjdbc.resultset");
    private V2_AsciiTableRenderer renderer;

    private ResultSetTablePrinter() {
        renderer = new V2_AsciiTableRenderer();

        renderer.setTheme(V2_E_TableThemes.PLAIN_7BIT.get());
        renderer.setWidth(new WidthLongestLine());
    }

    @Override
    public void printResultSet(LogContext context) {
        LogJdbcConfig config = context.getConfig();
        ResultSetData data = context.getResultSetCollector().getResultSetData();

        int resultSetMaxLength = config.getProperties().getResultsetMaxlength();
        int printResultSetSize = Math.min(resultSetMaxLength, data.getRowsSize());

        String resultSetTable = getResultSetTable(data, printResultSetSize);

        rsLogger.debug("\n" + resultSetTable);
    }

    String getResultSetTable(ResultSetData data, int printResultSetSize) {
        List<String> columns = data.getColumns();
        List<String[]> rowValues = data.getRows();

        V2_AsciiTable table = new V2_AsciiTable();

        table.addRule();
        table.addRow(columns.toArray());
        table.addStrongRule();

        for (int idx = 0; idx < printResultSetSize; idx++)
            table.addRow((Object[]) rowValues.get(idx));

        table.addRule();
        return renderer.render(table).toString();
    }
}
