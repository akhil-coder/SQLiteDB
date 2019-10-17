package com.appface.akhil.sqlitedb.Beans;

import java.sql.Blob;

public class ToDO {

    int id;
    String description;
    byte[] image;

    public ToDO(int id, String task, byte[] image) {
        this.id = id;
        this.description = task;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getTask() {
        return description;
    }

    public void setTask(String task) {
        description = task;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
