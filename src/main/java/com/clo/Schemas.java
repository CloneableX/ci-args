package com.clo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Schemas {
    private final List<Schema> schemasTemp;

    public Schemas(String schemasLine) {
        this.schemasTemp = Arrays.stream(schemasLine.split(";"))
                .map(Schema::new)
                .collect(Collectors.toList());
    }

    public int size() {
        return schemasTemp.size();
    }

    public Optional<Schema> findSchema(String label) {
        return schemasTemp.stream()
                .filter(schema -> schema.equalsLabel(label))
                .findFirst();
    }
}
