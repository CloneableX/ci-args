package com.clo;

import java.util.Arrays;

public enum SchemaTypeEnum {
    BOOL("bool", Boolean.TRUE), INT("int", 0), STRING("string", "");
    private String name;
    private Object defaultValue;

    SchemaTypeEnum(String name, Object defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public static SchemaTypeEnum findType(String name) {
        SchemaTypeEnum[] types = values();
        return Arrays.stream(types)
                .filter(type -> type.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public Object getDefaultValue() {
        return this.defaultValue;
    }
}
