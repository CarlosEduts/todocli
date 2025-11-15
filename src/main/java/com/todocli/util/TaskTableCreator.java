package com.todocli.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

public class TaskTableCreator {
    public static void create(Connection conn) {
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
}
