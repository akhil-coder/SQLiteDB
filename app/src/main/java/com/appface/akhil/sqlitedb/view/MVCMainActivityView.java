package com.appface.akhil.sqlitedb.view;

import com.appface.akhil.sqlitedb.Beans.ToDO;

import java.util.List;

public interface MVCMainActivityView extends MVCView
{
    public void showAllToDos(List<ToDO> toDOList);

    public void bindDataToView();

    public void updateViewOnAdd(List<ToDO> toDOList);

    public void updateViewOnRemove(List<ToDO> toDOList);

    public void updateViewOnModify(List<ToDO> toDOList);

    public void showErrorToast(String errorMessage);

}
