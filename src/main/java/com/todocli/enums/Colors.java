package com.todocli.enums;

public enum Colors {
    GREEN("\033[92m"),
    RED("\033[91m"),
    YELLOW("\033[93m"),
    BLUE("\033[94m"),
    ORANGE("\033[38;5;208m"), // 256-colors
    PINK("\033[95m");

    private final String code;

    Colors(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}