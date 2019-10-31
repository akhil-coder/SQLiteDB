package com.appface.akhil.sqlitedb.model;

import com.appface.akhil.sqlitedb.beans.ToDO;
import com.appface.akhil.sqlitedb.model.sqlite_db.ToDoListDBAdapter;

import java.util.ArrayList;
import java.util.List;

public class MVCModelImplementor implements MVCModel {

    private static final int EXIT_CODE = 0;
    private static final int MODIFY_CODE = 2;
    private static final int ADD_CODE = 1;
    private ToDoListDBAdapter toDoListDBAdapter;
    private List<ToDO> toDOList;
    List<Observer> observerList;

    private static final String TAG = "MVCModelImplementor";
    public MVCModelImplementor(ToDoListDBAdapter toDoListDBAdapter) {
        this.toDoListDBAdapter = toDoListDBAdapter;
        this.toDOList = toDoListDBAdapter.getAllToDos();
        observerList = new ArrayList<>();
    }

    public void registerObserver(Observer observer)
    {
        observerList.add(observer);
    }

    public List<ToDO> refresh() throws Exception {
        toDOList.clear();
        toDOList = toDoListDBAdapter.getAllToDos();
        return getAllToDos();
    }

    public ToDO refresh(int id) throws Exception {
        toDOList.clear();
        toDOList = toDoListDBAdapter.getAllToDos();
        return getToDo(id);
    }

    @Override
    public boolean modifyToDoItem(int id, String newToDoValue) throws Exception{
        boolean modify = toDoListDBAdapter.modify(id, newToDoValue);
        if(modify)
            notifyObservers(MODIFY_CODE);
        else
            throw new Exception("Something went wrong");
        return modify;
    }

    @Override
    public boolean removeToDo(int id) throws Exception {
        boolean delete = toDoListDBAdapter.delete((id));
        if(delete)
            notifyObservers(EXIT_CODE);
        else
            throw new Exception("Something went wrong");
        return delete;
    }

    @Override
    public boolean addNewToDo(String toDoitem) throws Exception {
        boolean insert = toDoListDBAdapter.insert(toDoitem);
        if(insert) {
            refresh();
            notifyObservers(ADD_CODE);
        }
        else
            throw new Exception("Something went wrong");
        return insert;
    }

    private void notifyObservers(int code) throws Exception {
        for(Observer observer: observerList) {
            observer.update(code);
        }
    }
    
    @Override
    public List<ToDO> getAllToDos() throws Exception {
        if(this.toDOList!=null && this.toDOList.size()>0)
            return this.toDOList;
        else
            throw new Exception("Empty to do list");
    }

    @Override
    public ToDO getToDo(int toDoId) throws Exception {
        ToDO toDo = null;
        for(ToDO toDo1: toDOList){
            if(toDo1.getId()==toDoId){
                toDo = toDo1;
                break;
            }
        }
        if(toDo==null){
            throw new Exception("Id is wrong" + toDoId);
        }
        return toDo;
    }
}
