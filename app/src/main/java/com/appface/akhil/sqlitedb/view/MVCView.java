package com.appface.akhil.sqlitedb.view;

import android.view.View;

public interface MVCView {

    public View getRootView();

    public void initView();

    void bindDataToView();
}
