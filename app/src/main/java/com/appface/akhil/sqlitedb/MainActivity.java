package com.appface.akhil.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appface.akhil.sqlitedb.Beans.ToDO;
import com.appface.akhil.sqlitedb.SQLite_DB.ToDoListDBAdapter;
import com.appface.akhil.sqlitedb.view.MainActivityViewImplementor;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    MainActivityViewImplementor mainActivityViewImplementor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewImplementor = new MainActivityViewImplementor(MainActivity.this, null);
        setContentView(mainActivityViewImplementor.getRootView());
        mainActivityViewImplementor.initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityViewImplementor.bindDataToView();
    }
}
