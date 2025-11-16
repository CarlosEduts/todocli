package com.todocli.util;

public class CliUtils {
    public static void clear() {
        System.out.print("\033[0m");
        for (int i = 0; i < 50; i++) System.out.println();
        System.out.flush();
    }

    public static String truncateOrPad(String input, int maxLength) {
        if (input.length() < maxLength) {
            return input + " ".repeat(maxLength - input.length());
        } else {
            return input.substring(0, maxLength - 3) + "...";
        }
    }
}
