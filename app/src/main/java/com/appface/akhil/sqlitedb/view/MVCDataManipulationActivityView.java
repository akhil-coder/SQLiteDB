package com.appface.akhil.sqlitedb.view;

import com.appface.akhil.sqlitedb.beans.ToDO;

public interface MVCDataManipulationActivityView extends MVCView {

    public void showSelectedToDo();
    public void updateViewOnRemove();
    public void updateViewOnModify(ToDO toDo);
    public void showErrorToast(String errorMessage);
}
