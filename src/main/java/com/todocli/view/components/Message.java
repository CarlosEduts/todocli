package com.todocli.view.components;

import com.todocli.util.CliUtils;
import com.todocli.enums.Colors;

public class Message {
    public static void info(String message) {
        print(Colors.BLUE, message);
    }

    public static void success(String message) {
        print(Colors.GREEN, message);
    }

    public static void alert(String message) {
        print(Colors.ORANGE, message);
    }

    public static void error(String message) {
        print(Colors.RED, message);
    }

    private static void print(Colors color, String message) {
        String border = "═".repeat(message.length() + 28);

        CliUtils.clear();
        System.out.println(color.getCode());
        System.out.println("╔" + border + "╗");

        System.out.println("║              " + message.toUpperCase() + "              ║");
        System.out.println("╚" + border + "╝");
        System.out.println("\033[0m");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
