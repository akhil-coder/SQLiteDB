package com.appface.akhil.sqlitedb.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appface.akhil.sqlitedb.DataManipulationActivity;
import com.appface.akhil.sqlitedb.beans.ToDO;
import com.appface.akhil.sqlitedb.R;
import com.appface.akhil.sqlitedb.controller.MVCMainActivityController;
import com.appface.akhil.sqlitedb.model.Observer;
import com.appface.akhil.sqlitedb.model.sqlite_db.ToDoListDBAdapter;
import com.appface.akhil.sqlitedb.controller.MVCController;
import com.appface.akhil.sqlitedb.model.MVCModelImplementor;
import com.appface.akhil.sqlitedb.view.adapter.ToDoAdapter;

import java.util.List;

public class MainActivityViewImplementor implements MVCMainActivityView, ToDoListItemMVCImpl.ListItemClickListener {

    private static final String TAG = "MainActivityViewImpleme";

    View rootView;
    MVCMainActivityController mvcController;
    private MVCModelImplementor mvcModel;

    private EditText et_add, et_modify, et_removeid;
    private Button btn_add, btn_modify, btn_remove;
    private TextView tv_display;
    private ToDoAdapter toDoAdapter;
    private RecyclerView recyclerView;

    public MainActivityViewImplementor(Context context, ViewGroup viewGroup) {
        this.rootView = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup);
        mvcModel = new MVCModelImplementor(ToDoListDBAdapter.getToDoListDBAdapterInstance(context));
        mvcController = new MVCMainActivityController(mvcModel, this);
    }

    @Override
    public void initView() {
        et_add = rootView.findViewById(R.id.et_add);
        btn_add = rootView.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mvcController.onAddButtonClicked(et_add.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerListViewToDos);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showAllToDos(List<ToDO> toDOList) {
        toDoAdapter = new ToDoAdapter(rootView.getContext(), toDOList,this);
        recyclerView.setAdapter(toDoAdapter);
    }

    @Override
    public void bindDataToView() {
        mvcController.onViewLoaded();
    }

    @Override
    public void updateViewOnAdd(List<ToDO> toDOList) {
        this.showAllToDos(toDOList);
    }

    @Override
    public void updateViewOnRemove() {
        try {
            this.showAllToDos(this.mvcModel.refresh());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateViewOnModify(List<ToDO> toDOList) {
        this.showAllToDos(toDOList);
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText( rootView.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToDataManipulationActivity(int id) {
        Intent intent = new Intent(rootView.getContext(), DataManipulationActivity.class);
        intent.putExtra("todoId", id);
        rootView.getContext().startActivity(intent);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void onItemClicked(int position) {
        mvcController.onToDoItemSelected(position);
    }
}
