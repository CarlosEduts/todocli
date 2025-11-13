package com.todocli.view.enums;

import java.util.Arrays;
import java.util.Optional;

public enum HomeOption {
    LIST('L'),
    CREATE('C'),
    FILTER('F'),
    SEARCH('S'),
    QUIT('Q');

    private final char code;

    HomeOption(char code) {
        this.code = code;
    }

    public char code() {
        return code;
    }

    public static Optional<HomeOption> fromChar(char c) {
        return Arrays.stream(values())
                .filter(o -> o.code == Character.toUpperCase(c))
                .findFirst();
    }
}