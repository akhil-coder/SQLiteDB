package com.appface.akhil.sqlitedb.controller;

import com.appface.akhil.sqlitedb.model.MVCModelImplementor;
import com.appface.akhil.sqlitedb.view.DataManipulationActivityViewImplementor;

public class MVCDataManipulationController implements MVCController {

    MVCModelImplementor mvcModelImplementor;
    DataManipulationActivityViewImplementor dataManipulationActivityViewImplementor;

    public MVCDataManipulationController(MVCModelImplementor mvcModelImplementor, DataManipulationActivityViewImplementor dataManipulationActivityViewImplementor) {
        this.mvcModelImplementor = mvcModelImplementor;
        this.dataManipulationActivityViewImplementor = dataManipulationActivityViewImplementor;
    }

    public void onRemoveButtonClicked(int toDoId) throws Exception {
        mvcModelImplementor.removeToDo(toDoId);
    }

    public void onModifyButtonClicked(int toDoId, String s) throws Exception {
        mvcModelImplementor.modifyToDoItem(toDoId, s);
    }

    @Override
    public void onViewLoaded() {
        dataManipulationActivityViewImplementor.showSelectedToDo();
    }
}
