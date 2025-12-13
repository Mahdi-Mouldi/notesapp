package com.example.appnotes.db;


public class Task {

    private int id;
    private int userId;
    private String task;
    private String category;
    private String priority;
    private String notes;
    private String dueDate;
    private String dueTime;
    private int completed;

    public Task(int id, int userId, String task, String category,
                String priority, String notes,
                int completed) {

        this.id = id;
        this.userId = userId;
        this.task = task;
        this.category = category;
        this.priority = priority;
        this.notes = notes;
        this.dueDate = dueDate;
        this.dueTime = dueTime;
        this.completed = completed;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getTask() { return task; }
    public String getCategory() { return category; }
    public String getPriority() { return priority; }
    public String getNotes() { return notes; }
    public String getDueDate() { return dueDate; }
    public String getDueTime() { return dueTime; }
    public int isCompleted() { return completed; }
}
