package com.clo;

public class Argument {
    private String label;
    private Object value;

    public Argument(String label, Object value) {
        this.label = label;
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean equalsLabel(String label) {
        return this.label.equals(label);
    }
}
