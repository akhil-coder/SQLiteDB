package com.appface.akhil.sqlitedb.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appface.akhil.sqlitedb.Beans.ToDO;
import com.appface.akhil.sqlitedb.R;
import com.appface.akhil.sqlitedb.SQLite_DB.ToDoListDBAdapter;
import com.appface.akhil.sqlitedb.controller.MVCController;
import com.appface.akhil.sqlitedb.model.MVCModel;
import com.appface.akhil.sqlitedb.model.MVCModelImplementor;

import java.util.List;

public class MainActivityViewImplementor implements MVCMainActivityView {

    View rootView;
    MVCController mvcController;
    private MVCModelImplementor mvcModel;

    private EditText et_add, et_modify, et_removeid;
    private Button btn_add, btn_modify, btn_remove;
    private TextView tv_display;

    public MainActivityViewImplementor(Context context, ViewGroup viewGroup) {
        this.rootView = LayoutInflater.from(context).inflate(R.layout.activity_main, viewGroup);
        mvcModel = new MVCModelImplementor(ToDoListDBAdapter.getToDoListDBAdapterInstance(context));
        mvcController = new MVCController(mvcModel, this);
    }


    @Override
    public void initView() {

        et_add = rootView.findViewById(R.id.et_add);
        et_modify = rootView.findViewById(R.id.et_modify);
        et_removeid = rootView.findViewById(R.id.et_removeid);

        tv_display = rootView.findViewById(R.id.tv_display);

        btn_add = rootView.findViewById(R.id.btn_add);
        btn_modify = rootView.findViewById(R.id.btn_modify);
        btn_remove = rootView.findViewById(R.id.btn_remove);

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


        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mvcController.onModifyButtonClicked(Integer.parseInt(et_removeid.getText().toString()), et_add.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mvcController.onRemoveButtonClicked(Integer.parseInt(et_removeid.getText().toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void showAllToDos(List<ToDO> toDOList) {
        tv_display.setText(toDOList.toString());
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
    public void updateViewOnRemove(List<ToDO> toDOList) {
        this.showAllToDos(toDOList);
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
    public View getRootView() {
        return rootView;
    }




}
