package net.chandol.logjdbc.logging.collector.parameter;

/**
 * 입력 파라미터유형
 */
public enum ParameterType {
    _BigDecimal, _Boolean, _Byte, _Date, _Double, _Float, _Int, _Long, _Null, _Ref, _RowId, _Short, _SQLXML, _String, _Time, _Timestamp, _URL,

    // 파싱이 불가능한 타입
    _Object(false), _Array(false), _Bytes(false), _AsciiStream(false), _BinaryStream(false), _Blob(false), _CharacterStream(false), _Clob(false), _NCharacterStream(false), _NClob(false), _NString(false), UnicodeStream(false);

    boolean convertible;

    ParameterType(boolean convertible) {
        this.convertible = convertible;
    }

    ParameterType() {
        this.convertible = true;
    }

    public boolean isConvertible() {
        return convertible;
    }

    public boolean match(ParameterType... types) {
        for (ParameterType type : types){
            if (this == type) {
                return true;
            }
        }

        return false;
    }

    public String getTypeAsStr() {
        return this.name().substring(1);
    }
}
