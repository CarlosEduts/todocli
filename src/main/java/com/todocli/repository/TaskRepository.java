package com.todocli.repository;

import com.todocli.config.ConnectionFactory;
import com.todocli.model.Task;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public static void createTable(Connection conn) {
        String sql = """
                    CREATE TABLE IF NOT EXISTS tasks (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        date DATE NOT NULL,
                        time TIME NULL,
                        title VARCHAR(255) NOT NULL,
                        description TEXT NULL,
                        completed TINYINT(1) NOT NULL DEFAULT 0,
                        deleted TINYINT(1) NOT NULL DEFAULT 0,
                        PRIMARY KEY (id)
                    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
                """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("[util] " + LocalTime.now() + " - Tabela 'tasks' pronta para uso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void save(Task task) {
        String sql = "INSERT INTO tasks (date, time, title, description, completed, deleted) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(2, task.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            stmt.setString(3, task.getTitle());
            stmt.setString(4, task.getDescription());
            stmt.setBoolean(5, false);
            stmt.setBoolean(6, false);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("[model] " + e);
        }
    }

    public static void delete(Task task) {
        String sql = "UPDATE tasks SET deleted = 1 WHERE (id = ?);";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void markAsComplete(Task task) {
        String sql = "UPDATE tasks SET completed = 1 WHERE (id = ?);";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Task> findAll() {
        final String sql = "SELECT * FROM tasks;";
        List<Task> tasks = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getTime("time").toLocalTime(),
                        rs.getString("title"), rs.getString("description"),
                        rs.getBoolean("completed"),
                        rs.getBoolean("deleted"))
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuários", e);
        }

        return tasks;
    }
}
