package com.clo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Args {
    private final List<Argument> arguments;

    public Args(String argsLine, Schemas schemas) {
        String[] argElements = argsLine.split(" ");
        this.arguments = IntStream.range(0, argsLine.split(" ").length)
                .filter(index -> isLabel(argsLine.split(" ")[index]))
                .mapToObj(index -> parseArg(index, schemas, argElements))
                .collect(Collectors.toList());
    }

    private Argument parseArg(int index, Schemas schemas, String[] argElements) {
        String label = argElements[index].replaceFirst("-", "");
        Schema schema = schemas.findSchema(label)
                .orElseThrow(() -> new InvalidLabelException("invalid label:" + label));

        if (index < argElements.length - 1 && !isLabel(argElements[index + 1])) {
            return new Argument(label, schema.parseValue(argElements[index + 1]));
        }
        return new Argument(label, schema.getDefaultValue());
    }

    public int size() {
        return arguments.size();
    }

    public Argument findArg(String label) {
        return arguments.stream()
                .filter(argument -> argument.equalsLabel(label))
                .findFirst()
                .orElse(null);
    }

    private boolean isLabel(String arg) {
        return arg.contains("-");
    }

}
