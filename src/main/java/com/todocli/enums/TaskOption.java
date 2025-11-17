package com.todocli.enums;

import java.util.Arrays;
import java.util.Optional;

public enum TaskOption {
    EDIT('E'),
    DELETE('D'),
    MARK_DONE('M'),
    BACK('B'),
    RESTORE('R');

    private final char code;

    TaskOption(char code) {
        this.code = code;
    }

    public char code() {
        return code;
    }

    public static Optional<TaskOption> fromChar(char c) {
        return Arrays.stream(values())
                .filter(o -> o.code == Character.toUpperCase(c))
                .findFirst();
    }
}