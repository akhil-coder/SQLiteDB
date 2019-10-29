package com.appface.akhil.sqlitedb.controller;

import com.appface.akhil.sqlitedb.MainActivity;
import com.appface.akhil.sqlitedb.model.MVCModelImplementor;
import com.appface.akhil.sqlitedb.view.MainActivityViewImplementor;

public class MVCController {

    MVCModelImplementor mvcModel;
    MainActivityViewImplementor  mvcView;

    public MVCController(MVCModelImplementor mvcModelImplementor, MainActivityViewImplementor mvcView) {
        this.mvcModel = mvcModelImplementor;
        this.mvcView = mvcView;
    }

    public void onViewLoaded()
    {
        try {
            mvcView.showAllToDos(mvcModel.getAllToDos());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onAddButtonClicked(String toDoItem) throws Exception {
        boolean addNewToDo = mvcModel.addNewToDo(toDoItem);
        if(addNewToDo)
        {
            try {
                mvcView.updateViewOnAdd(mvcModel.getAllToDos());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onRemoveButtonClicked(int id)
    {
        boolean success = false;
        try {
            success = mvcModel.removeToDo(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(success){
            try {
                mvcView.updateViewOnRemove(mvcModel.getAllToDos());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onModifyButtonClicked(int id, String newValue){
        boolean modify = false;
        try {
            modify = mvcModel.modifyToDoItem(id, newValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(modify){
            try {
                mvcView.updateViewOnModify(mvcModel.getAllToDos());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }    }
}
