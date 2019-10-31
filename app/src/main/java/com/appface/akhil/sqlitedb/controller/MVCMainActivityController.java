package com.appface.akhil.sqlitedb.controller;

import com.appface.akhil.sqlitedb.model.MVCModelImplementor;
import com.appface.akhil.sqlitedb.view.MainActivityViewImplementor;

public class MVCMainActivityController implements MVCController {

    MVCModelImplementor mvcModel;
    MainActivityViewImplementor  mvcView;

    public MVCMainActivityController(MVCModelImplementor mvcModelImplementor, MainActivityViewImplementor mvcView) {
        this.mvcModel = mvcModelImplementor;
        this.mvcView = mvcView;
    }

    @Override
    public void onViewLoaded()
    {
        try {
            mvcView.showAllToDos(mvcModel.refresh());
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

    public void onToDoItemSelected(int position) {
        mvcView.navigateToDataManipulationActivity(position);
    }
}
