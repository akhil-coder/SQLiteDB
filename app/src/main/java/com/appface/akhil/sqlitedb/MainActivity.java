package com.appface.akhil.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appface.akhil.sqlitedb.Beans.ToDO;
import com.appface.akhil.sqlitedb.SQLite_DB.ToDoListDBAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ToDoListDBAdapter toDoListDBAdapter;
    private List<ToDO> toDos;
    private EditText et_add, et_modify, et_removeid;
    private Button btn_add, btn_modify, btn_remove;
    private TextView tv_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoListDBAdapter = ToDoListDBAdapter.getToDoListDBAdapterInstance(getApplicationContext());
        toDos = toDoListDBAdapter.getAllToDos();

        tv_display = findViewById(R.id.tv_display);
        et_add = findViewById(R.id.et_add);
        et_modify = findViewById(R.id.et_modify);
        et_removeid = findViewById(R.id.et_removeid);

        btn_add = findViewById(R.id.btn_add);
        btn_modify = findViewById(R.id.btn_modify);
        btn_remove = findViewById(R.id.btn_remove);

        btn_add.setOnClickListener(this);
        btn_modify.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        setNewList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: addNewToDo(); break;
            case R.id.btn_remove: removeToDo(); break;
            case R.id.btn_modify: modifyToDo(); break;
            default: break;
        }
    }

    private void setNewList(){
        tv_display.setText(getToDolistString());
    }

    private String getToDolistString() {
        toDos = toDoListDBAdapter.getAllToDos();
        if(toDos != null && toDos.size() > 0){
            StringBuilder stringBuilder = new StringBuilder();
            for(ToDO toDO: toDos){
                stringBuilder.append(toDO.getId() + ". " + toDO.getTask() + "\n");
            }
            return stringBuilder.toString();
        } else {
            return "No Task Items";
        }
    }

    private void modifyToDo() {
        int id = Integer.parseInt(et_removeid.getText().toString());
        String task = et_modify.getText().toString();
        toDoListDBAdapter.modify(id, task);
        setNewList();
    }

    private void removeToDo() {
        toDoListDBAdapter.delete(Integer.parseInt(et_removeid.getText().toString()));
        setNewList();
    }

    private void addNewToDo() {
        toDoListDBAdapter.insert(et_add.getText().toString());
        setNewList();
    }
}
