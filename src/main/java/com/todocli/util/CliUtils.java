package com.todocli.util;

public class CliUtils {
    public static void clear() {
        System.out.print("\033[0m");
        for (int i = 0; i < 50; i++) System.out.println();
        System.out.flush();
    }
}
