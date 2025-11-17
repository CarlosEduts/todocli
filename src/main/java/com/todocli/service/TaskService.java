package com.todocli.service;

import com.todocli.model.Task;
import com.todocli.repository.TaskRepository;
import com.todocli.enums.HomeOption;
import com.todocli.enums.TaskOption;
import com.todocli.view.components.*;

import java.util.List;

public class TaskService {
    public static void home() {
        HomeOption homeOption = Home.show();
        switch (homeOption) {
            case LIST -> tasks();
            case CREATE -> create();
            case DELETED_LIST -> deletedTasks();
        }
    }

    /* ------------ MÉTODOS PRIVADOS AUXILIARES ------------ */
    private static void create() {
        try {
            Task task = CreateTask.show();
            TaskRepository.save(task);
            Message.success("Tarefa criada com sucesso!");
            home();
        } catch (RuntimeException e) {
            Message.error("Erro ao criar a tarefa, por favor tente novamente");
            home();
        }
    }

    private static void tasks() {
        List<Task> tasks = TaskRepository.findAll();
        tasks = tasks.stream().filter(task -> !task.isDeleted()).toList();
        Task selectedTask = TasksList.show(tasks, "MINHAS TAREFAS");

        while (true) {
            TaskOption taskOption = TaskDetail.show(selectedTask);

            switch (taskOption) {
                case MARK_DONE -> {
                    if (ConfirmBox.taskComplete(selectedTask)) {
                        TaskRepository.markAsComplete(selectedTask);
                        Message.success("Tarefa marcada como concluída.");
                    } else {
                        Message.alert("Ação cancelada.");
                    }
                }
                case DELETE -> {
                    if (ConfirmBox.taskDelete(selectedTask)) {
                        TaskRepository.delete(selectedTask);
                        Message.success("Tarefa deletada com sucesso.");
                        tasks(); // Retorna para a lista de tarefas
                    } else {
                        Message.alert("Ação cancelada.");
                    }
                }
                case BACK -> {
                    home();
                    return;
                }
            }
        }
    }

    private static void deletedTasks() {
        List<Task> tasks = TaskRepository.findAll();
        tasks = tasks.stream().filter(Task::isDeleted).toList();
        Task selectedTask = TasksList.show(tasks, "TAREFAS EXCLUÍDAS");

        while (true) {
            TaskOption taskOption = TaskDetail.show(selectedTask);

            switch (taskOption) {
                case RESTORE -> {
                    if (ConfirmBox.taskRestore(selectedTask)) {
                        TaskRepository.restore(selectedTask);
                        Message.success("Tarefa restaurada com sucesso.");
                        tasks(); // Retorna para a lista de tarefas
                    } else {
                        Message.alert("Ação cancelada.");
                    }
                }
                case BACK -> {
                    home();
                    return;
                }
            }
        }
    }
}
