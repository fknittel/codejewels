package net.amygdalum.codejewels.constructors;

import static java.util.stream.Collectors.joining;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test {

    private static final int K = 0;

    private int j;

    Result method(Argument argument) throws AnException, IOException {
        Argument arg = argument;
        while (arg != null) {
            argument.i = arg.i;
            arg = arg.next;
        }
        if (argument.i == 0) {
            System.err.println("error");
            throw new AnException();
        }
        String s = "i+j+K=" + (argument.i + this.j + Test.K) + Files.lines(Paths.get("unit")).collect(joining());
        return new Result(s);
    }
}
