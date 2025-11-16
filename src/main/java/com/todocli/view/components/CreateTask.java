package com.todocli.view.components;

import com.todocli.model.Task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class CreateTask {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Task show() {
        System.out.println("""
                ╔════════════════════════════════════════╗
                ║           CRIAR NOVA TAREFA            ║
                ╚════════════════════════════════════════╝
                """);

        LocalDate date = readDate();
        LocalTime time = readTime();
        String title = readNonEmpty();

        System.out.print("Descrição           : ");
        String description = SCANNER.nextLine();

        return new Task(0, date, time, title, description, false, false);
    }

    /* ------------ MÉTODOS PRIVADOS AUXILIARES ------------ */
    private static LocalDate readDate() {
        while (true) {
            System.out.print("Data (YYYY-MM-DD)   : ");
            try {
                return LocalDate.parse(SCANNER.nextLine());
            } catch (Exception e) {
                Message.error("Data inválida. Use o formato YYYY-MM-DD.");
            }
        }
    }

    private static LocalTime readTime() {
        while (true) {
            System.out.print("Hora (HH:MM)        : ");
            try {
                return LocalTime.parse(SCANNER.nextLine() + ":00");
            } catch (Exception e) {
                Message.error("Horário inválido. Use o formato HH:MM.");
            }
        }
    }

    private static String readNonEmpty() {
        while (true) {
            System.out.print("Título              : ");
            String value = SCANNER.nextLine();
            if (!value.isBlank()) return value;
            Message.error("Este campo não pode estar vazio.");
        }
    }
}
