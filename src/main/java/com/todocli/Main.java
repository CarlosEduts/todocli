package com.todocli;

import com.todocli.config.ConnectionFactory;
import com.todocli.repository.TaskRepository;
import com.todocli.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskRepository.createTable(ConnectionFactory.getConnection());
        TaskService.home();
    }
}
