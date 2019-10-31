package com.appface.akhil.sqlitedb.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appface.akhil.sqlitedb.R;
import com.appface.akhil.sqlitedb.beans.ToDO;
import com.appface.akhil.sqlitedb.controller.MVCDataManipulationController;
import com.appface.akhil.sqlitedb.model.MVCModelImplementor;
import com.appface.akhil.sqlitedb.model.Observer;
import com.appface.akhil.sqlitedb.model.sqlite_db.ToDoListDBAdapter;

public class DataManipulationActivityViewImplementor implements MVCDataManipulationActivityView, Observer {

    private static final String TAG = "DataManipulationActivit";
    View rootView;
    MVCModelImplementor mvcModelImplementor;
    MVCDataManipulationController mvcDataManipulationController;
    TextView textViewToBeModifiedToDoId, textViewToBeModifiedToDo;
    Button buttonRemoveToDo, buttonModifyToDo;
    EditText editTextNewToDo;
    int toDoId;
    ToDO toDo;
    private static final int EXIT_CODE = 0;
    private static final int MODIFY_CODE = 2;
    private static final int ADD_CODE = 1;
    Context mContext;

    public DataManipulationActivityViewImplementor(Context context, ViewGroup viewGroup, Intent intent) {
        rootView = LayoutInflater.from(context).inflate(R.layout.acivity_data_manipulate, viewGroup);
        mvcModelImplementor = new MVCModelImplementor(ToDoListDBAdapter.getToDoListDBAdapterInstance(context));
        mvcDataManipulationController = new MVCDataManipulationController(mvcModelImplementor, this);
        mvcModelImplementor.registerObserver(this);
        toDoId = intent.getIntExtra("todoId", 1);
        this.mContext = context;
    }

    @Override
    public void showSelectedToDo() {
        try {
            toDo = mvcModelImplementor.getToDo(toDoId);
            textViewToBeModifiedToDoId.setText("Id: " + toDo.getId());
            textViewToBeModifiedToDo.setText("ToDo: " + toDo.getTask());
        } catch (Exception ex) {
            Toast.makeText(rootView.getContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateViewOnRemove() {
        textViewToBeModifiedToDoId.setText("");
        textViewToBeModifiedToDo.setText("");
        Toast.makeText(rootView.getContext(), "Successfully removed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateViewOnModify(ToDO toDo) {
        this.toDo = toDo;
        textViewToBeModifiedToDo.setText(this.toDo.getTask());
        Toast.makeText(rootView.getContext(), "Successfully updated", Toast.LENGTH_LONG).show();
    }


    @Override
    public void showErrorToast(String errorMessage) {

    }

    @Override
    public void update(int code) throws Exception {
        switch (code) {
            case MODIFY_CODE:
                toDo = mvcModelImplementor.refresh(toDoId);
                textViewToBeModifiedToDo.setText("ToDo: " + toDo.getTask());
                break;
            case EXIT_CODE:
                break;
        }
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initView() {
        textViewToBeModifiedToDoId = (TextView) rootView.findViewById(R.id.textViewToBeModifiedToDoId);
        textViewToBeModifiedToDo = (TextView) rootView.findViewById(R.id.textViewToBeModifiedToDo);

        buttonRemoveToDo = (Button) rootView.findViewById(R.id.buttonRemoveToDo);
        buttonModifyToDo = (Button) rootView.findViewById(R.id.buttonModifyToDo);
        editTextNewToDo = (EditText) rootView.findViewById(R.id.editTextNewToDo);
        buttonRemoveToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mvcDataManipulationController.onRemoveButtonClicked(toDoId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        buttonModifyToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mvcDataManipulationController.onModifyButtonClicked(toDoId, editTextNewToDo.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void bindDataToView() {
        mvcDataManipulationController.onViewLoaded();
    }

}
