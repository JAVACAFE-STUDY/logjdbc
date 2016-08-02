package net.chandol.logjdbc.logging.collector.resultset;

// Collector에서만 사용되는 협력객체. 외부로는 공개하지 않는다.
// 컬럼정보를 담는 valueObject
class Column {
    private String label;
    private String type;

    Column(String label, String type) {
        this.label = label;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }
}