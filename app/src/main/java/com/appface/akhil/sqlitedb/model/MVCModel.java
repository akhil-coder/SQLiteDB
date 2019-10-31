package com.appface.akhil.sqlitedb.model;

import com.appface.akhil.sqlitedb.beans.ToDO;

import java.util.List;

public interface MVCModel {

    public boolean modifyToDoItem(int id, String newToDoValue) throws Exception;
    public boolean removeToDo(int id) throws Exception;
    public boolean addNewToDo(String toDoItem) throws Exception;
    public List<ToDO> getAllToDos() throws Exception;
    ToDO getToDo(int toDoId) throws Exception;
}
