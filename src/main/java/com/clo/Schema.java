package com.clo;

import java.util.Objects;

import static com.clo.SchemaTypeEnum.BOOL;
import static com.clo.SchemaTypeEnum.INT;

public class Schema {
    private SchemaTypeEnum type;
    private String label;

    public Schema(String schemaLine) {
        String[] schemaArgs = schemaLine.split(":");
        this.label = schemaArgs[0];
        this.type = SchemaTypeEnum.findType(schemaArgs[1]);
    }

    public Schema(String label, SchemaTypeEnum type) {
        this.label = label;
        this.type = type;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Schema schema = (Schema) otherObject;
        return Objects.equals(label, schema.label) &&
                Objects.equals(type, schema.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, type);
    }

    public boolean equalsLabel(String label) {
        return this.label.equals(label);
    }

    public Object getDefaultValue() {
        return type.getDefaultValue();
    }

    public Object parseValue(String value) {
        if (type == INT) {
            return new Integer(value);
        }

        if (type == BOOL) {
            return Boolean.valueOf(value);
        }

        return value;
    }
}
