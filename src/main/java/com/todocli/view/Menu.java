package com.todocli.view;

import com.todocli.view.enums.HomeOption;
import com.todocli.model.Task;
import com.todocli.view.enums.Colors;
import com.todocli.view.enums.TaskOption;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static HomeOption home() {
        while (true) {
            Components.home();
            System.out.print("\nAção selecionada >> ");
            char selected = scanner.next().toUpperCase().charAt(0);

            Optional<HomeOption> option = HomeOption.fromChar(selected);
            if (option.isPresent()) {
                return option.get();
            }

            Components.info(Colors.ERROR, "Ação indisponível, selecione uma ação válida");
        }
    }

    public Task listTasks(List<Task> tasks) {
        Task selectedTask = null;
        int selectedId;

        while (true) {
            Components.tasks(tasks);
            System.out.print("\nID selecionado >> ");
            selectedId = scanner.nextInt();
            scanner.nextLine();

            for (Task task : tasks) {
                if (task.getId() == selectedId) {
                    selectedTask = task;
                    return selectedTask;
                }
            }

            Components.info(Colors.ERROR, "ID indisponível, selecione um ID válido");
        }

    }

    public static TaskOption taskDetails(Task task) {
        while (true) {
            Components.task(task);
            System.out.print("\nAção selecionada >> ");
            char selected = scanner.next().toUpperCase().charAt(0);

            Optional<TaskOption> option = TaskOption.fromChar(selected);
            if (option.isPresent()) {
                return option.get();
            }

            Components.info(Colors.ERROR, "Ação indisponível, selecione uma ação válida");
        }
    }
}
