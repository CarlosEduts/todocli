package com.todocli.view.enums;

public enum Colors {
    SUCCESS("\033[92m"),  // verde
    ERROR("\033[91m"),    // vermelho
    WARNING("\033[93m");  // amarelo

    private final String color;

    Colors(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
