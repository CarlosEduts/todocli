package com.todocli.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private long id;
    private LocalDate date;
    private LocalTime time;
    private String title;
    private String description;
    private boolean completed;
    private boolean deleted;

    public Task(long id, LocalDate date, LocalTime time, String title, String description, boolean completed, boolean deleted) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.deleted = deleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
