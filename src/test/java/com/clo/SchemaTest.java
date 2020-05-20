package com.clo;

import org.junit.Test;

import static com.clo.SchemaTypeEnum.BOOL;
import static com.clo.SchemaTypeEnum.INT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchemaTest {
    @Test
    public void should_get_schema_when_give_schema_line() {
        checkSchema("l:bool", "l", BOOL);
        checkSchema("p:int", "p", INT);
    }

    @Test
    public void should_equal_label_when_give_schema_line() {
        checkSameLabelSchema("l:bool", "l");
        checkSameLabelSchema("p:bool", "p");
    }

    @Test
    public void should_get_default_value_boolean_true_when_give_bool_type_schema() {
        checkDefaultValue("l:bool", Boolean.TRUE);
    }

    @Test
    public void should_get_default_value_0_when_give_int_type_schema() {
        checkDefaultValue("p:int", 0);
    }

    @Test
    public void should_get_default_value_empty_string_when_give_string_type_schema() {
        checkDefaultValue("d:string", "");
    }

    private void checkDefaultValue(String s, Object expectedResult) {
        Schema schema = new Schema(s);
        assertThat(schema.getDefaultValue(), is(expectedResult));
    }

    private void checkSameLabelSchema(String schemaLine, String label) {
        Schema schema = new Schema(schemaLine);
        assertThat(schema.equalsLabel(label), is(Boolean.TRUE));
    }

    private void checkSchema(String schemaLine, String label, SchemaTypeEnum type) {
        Schema schema = new Schema(schemaLine);
        assertThat(schema, is(new Schema(label, type)));
    }
}
