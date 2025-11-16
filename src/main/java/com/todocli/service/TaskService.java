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
        }
    }

    private static void tasks() {
        List<Task> tasks = TaskRepository.findAll();
        tasks = tasks.stream().filter(task -> !task.isDeleted()).toList();
        Task selectedTask = TasksList.show(tasks);

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
