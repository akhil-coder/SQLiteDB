package com.appface.akhil.sqlitedb.model;

import com.appface.akhil.sqlitedb.Beans.ToDO;
import com.appface.akhil.sqlitedb.SQLite_DB.ToDoListDBAdapter;

import java.util.List;

public class MVCModelImplementor implements MVCModel {

    private ToDoListDBAdapter toDoListDBAdapter;
    private List<ToDO> toDOList;


    public MVCModelImplementor(ToDoListDBAdapter toDoListDBAdapter) {
        this.toDoListDBAdapter = toDoListDBAdapter;
        this.toDOList = toDoListDBAdapter.getAllToDos();
    }

    public  void refresh()
    {
        toDOList.clear();
        toDoListDBAdapter.getAllToDos();
    }

    @Override
    public boolean modifyToDoItem(int id, String newToDoValue) throws Exception{
        boolean modify = toDoListDBAdapter.modify(id, newToDoValue);
        if(modify)
            refresh();
        else
            throw new Exception("Something went wrong");
        return modify;

    }

    @Override
    public boolean removeToDo(int id) throws Exception {
        boolean delete = toDoListDBAdapter.delete((id));
        if(delete)
            refresh();
        else
            throw new Exception("Something went wrong");
        return delete;
    }

    @Override
    public boolean addNewToDo(String toDoitem) throws Exception {
        boolean insert = toDoListDBAdapter.insert(toDoitem);
        if(insert)
        refresh();
        else
            throw new Exception("Something went wrong");
        return insert;
    }

    @Override
    public List<ToDO> getAllToDos() throws Exception {
        if(this.toDOList!=null && this.toDOList.size()>0)
            return this.toDOList;
        else
            throw new Exception("Empty to do list");
    }
}
