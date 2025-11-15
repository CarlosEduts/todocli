package com.todocli.repository;

import com.todocli.config.ConnectionFactory;
import com.todocli.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    public static Task create(Task task) {
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

        return task;
    }

    public static void delete(int id) {
        String sql = "UPDATE tasks SET deleted = 1 WHERE (id = ?);";

        try (Connection conn = ConnectionFactory.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void markAsComplete(int id) {
        String sql = "UPDATE tasks SET completed = 1 WHERE (id = ?);";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
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
