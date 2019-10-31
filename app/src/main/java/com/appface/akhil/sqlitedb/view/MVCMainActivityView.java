package com.appface.akhil.sqlitedb.view;

import com.appface.akhil.sqlitedb.beans.ToDO;

import java.util.List;

public interface MVCMainActivityView extends MVCView
{
    public void showAllToDos(List<ToDO> toDOList);

    public void bindDataToView();

    public void updateViewOnAdd(List<ToDO> toDOList);

    public void updateViewOnRemove();

    public void updateViewOnModify(List<ToDO> toDOList);

    public void showErrorToast(String errorMessage);

    public void navigateToDataManipulationActivity(int id);

}
