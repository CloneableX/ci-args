package com.clo;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArgsTest {
    private Schemas schemas;

    @Before
    public void setUp() {
        schemas = new Schemas("l:bool;p:int;d:string");
    }

    @Test
    public void should_get_args_size_when_give_args_line() {
        checkArgsSize("-l false -p 8080 -d /usr/logs");
        checkArgsSize("-l -p 8080 -d /usr/logs");
    }

    @Test
    public void should_get_arg_value_when_give_arg_label() {
        checkArgumentValue("-l false -p 8080 -d /usr/logs", "l", Boolean.FALSE);
        checkArgumentValue("-l -p 8080 -d /usr/logs", "p", 8080);
        checkArgumentValue("-l -p 8080 -d /usr/logs", "d", "/usr/logs");
        checkArgumentValue("-l", "l", Boolean.TRUE);
    }

    @Test(expected = InvalidLabelException.class)
    public void should_throw_invalid_label_exception_when_parse_invalid_label() {
        new Args("-x test", schemas);
    }

    private void checkArgumentValue(String argsLine, String label, Object expectedResult) {
        Args args = new Args(argsLine, schemas);
        assertThat(args.findArg(label).getValue(), is(expectedResult));
    }

    private void checkArgsSize(String argsLine) {
        Args args = new Args(argsLine, schemas);
        assertThat(args.size(), is(3));
    }
}
