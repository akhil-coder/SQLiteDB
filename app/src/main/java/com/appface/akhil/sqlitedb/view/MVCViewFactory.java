package com.appface.akhil.sqlitedb.view;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

public class MVCViewFactory {

    public enum VIEW_TYPE {
        MAIN_VIEW_TYPE, MANIPULATION_VIEW_TYPE
    }

    public static MVCView getMVCView(VIEW_TYPE view_type, Context context, ViewGroup viewGroup, Intent intent) {
        MVCView mvcView = null;
        switch (view_type) {
            case MAIN_VIEW_TYPE:
                mvcView = new MainActivityViewImplementor(context, viewGroup);
                break;
            case MANIPULATION_VIEW_TYPE:
                mvcView = new DataManipulationActivityViewImplementor(context, viewGroup, intent);
                break;
        }
        return mvcView;
    }
}
