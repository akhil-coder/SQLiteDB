package com.appface.akhil.sqlitedb.beans;

public class ToDO {

    int Id;
    String Task;

    public ToDO(int id, String task) {
        Id = id;
        Task = task;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String task) {
        Task = task;
    }
}
