package com.appface.akhil.sqlitedb.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appface.akhil.sqlitedb.R;
import com.appface.akhil.sqlitedb.beans.ToDO;

public class ToDoListItemMVCImpl implements MVCListItemView<ToDO> {

    View rootView;
    ToDO toDO;
    public LinearLayout linearLayout;
    public TextView textViewId, textViewToDo, textViewPlace;

    ListItemClickListener listItemClickListener;


    public ToDoListItemMVCImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        rootView = layoutInflater.inflate(R.layout.todo_row_item, viewGroup, false);
    }

    @Override
    public void setListItemClickListener(ListItemClickListener listItemClickListener) {
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        linearLayout = rootView.findViewById(R.id.layoutContainer);
        textViewId = rootView.findViewById(R.id.textViewId);
        textViewToDo = rootView.findViewById(R.id.textViewToDo);
    }

    @Override
    public void bindDataToView(ToDO object) {
        this.toDO = object;
        textViewId.setText("ID: "+toDO.getId());
        textViewToDo.setText("ID: "+toDO.getTask());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listItemClickListener != null)
                {
                    listItemClickListener.onItemClicked(toDO.getId());
                }
            }
        });
    }
}
