package net.chandol.logjdbc.logging.printer.resultset;

import de.vandermeer.asciitable.v2.V2_AsciiTable;
import de.vandermeer.asciitable.v2.render.V2_AsciiTableRenderer;
import de.vandermeer.asciitable.v2.render.WidthLongestLine;
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes;
import net.chandol.logjdbc.config.LogJdbcConfig;
import net.chandol.logjdbc.logging.collector.resultset.ResultSetCollector;
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
    public void logResultSet(LogJdbcConfig config, ResultSetCollector collector) {
        ResultSetData resultSetData = collector.getResultSetData();

        List<String> columns = resultSetData.getColumns();
        List<String[]> rowValues = resultSetData.getDatas();

        V2_AsciiTable table = new V2_AsciiTable();

        table.addRule();
        table.addRow(columns.toArray());
        table.addStrongRule();

        for (String[] values : rowValues)
            table.addRow(values);

        table.addRule();

        rsLogger.debug("\n" + renderer.render(table).toString());
    }
}
