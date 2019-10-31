package com.appface.akhil.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.appface.akhil.sqlitedb.view.DataManipulationActivityViewImplementor;
import com.appface.akhil.sqlitedb.view.MVCView;
import com.appface.akhil.sqlitedb.view.MVCViewFactory;

public class DataManipulationActivity extends AppCompatActivity {

    DataManipulationActivityViewImplementor dataManipulationActivityViewImplementor;
    private MVCView mvcView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvcView = MVCViewFactory.getMVCView(MVCViewFactory.VIEW_TYPE.MANIPULATION_VIEW_TYPE, DataManipulationActivity.this, null, getIntent());
        setContentView(mvcView.getRootView());
        mvcView.initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcView.bindDataToView();

    }
}
