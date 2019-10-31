package com.appface.akhil.sqlitedb.view;

import android.view.View;

public interface MVCListItemView<T> {

    interface ListItemClickListener{
        void onItemClicked(int position);
    }

    void setListItemClickListener(ListItemClickListener listItemClickListener);
    View getRootView();
    void initViews();
    void bindDataToView(T object);
}
