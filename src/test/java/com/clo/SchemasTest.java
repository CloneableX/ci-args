package com.clo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchemasTest {
    @Test
    public void should_get_schemas_size_when_give_schemas_line() {
        checkSchemasSize("l:bool;p:int;d:string", 3);
        checkSchemasSize("p:int;d:string", 2);
    }

    @Test
    public void should_get_schema_type_when_give_schema_label() {
        Schemas schemas = new Schemas("l:bool;p:int;d:string");

        assertThat(schemas.findSchema("l").orElse(null), is(new Schema("l:bool")));
        assertThat(schemas.findSchema("p").orElse(null), is(new Schema("p:int")));
    }

    private void checkSchemasSize(String schemasLine, int expectedResult) {
        Schemas schemas = new Schemas(schemasLine);
        assertThat(schemas.size(), is(expectedResult));
    }
}
