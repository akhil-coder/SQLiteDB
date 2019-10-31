package com.appface.akhil.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appface.akhil.sqlitedb.view.MainActivityViewImplementor;

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
