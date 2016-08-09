package net.chandol.logjdbc.logging.collector.resultset;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import static net.chandol.logjdbc.logging.collector.resultset.ResultSetUtil.createColumns;

public class ResultSetData {
    private List<Column> columns;
    // list로 row를 표현 array로는 데이터를 표현
    // column의 크기는 고정되어있음. 어차피 log로 표현.String으로 써도 문제없음.
    private List<String[]> datas;

    ResultSetData(ResultSetMetaData metaData) {
        this.columns = createColumns(metaData);
        this.datas = new ArrayList<>();
    }

    // TODO ColumnType별로 문자열로 변경하여 저장한다.
    // 우선은 ToString을 사용한다.
    void addRow(Object... values) {
        String[] newValues = new String[values.length];
        for (int idx = 0; idx < values.length; idx++){
            if(values[idx] != null)
                newValues[idx] = values[idx].toString();
            else
                newValues[idx] = "<null>";
        }

        datas.add(newValues);
    }

    public List<String> getColumns() {
        List<String> columnLabels = new ArrayList<>();
        for (Column column : columns) {
            columnLabels.add(column.getLabel());
        }
        return columnLabels;
    }

    public List<String[]> getDatas() {
        return datas;
    }
}