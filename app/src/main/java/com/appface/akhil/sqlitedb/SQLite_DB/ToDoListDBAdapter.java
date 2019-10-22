package com.appface.akhil.sqlitedb.SQLite_DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.appface.akhil.sqlitedb.Beans.ImageRequest;

import java.util.ArrayList;
import java.util.List;

public class ToDoListDBAdapter {

    private static final String TAG = "ToDoListDBAdapter";

    private static final String DB_NAME = "todolist.db";
    private static final int DB_Version = 2;

    private static final String TABLE_TODO = "table_todo";
    private static final String COLUMN_TODO_ID = "task_id";
    private static final String COLUMN_TODO = "todo";
    private static final String COLUMN_IMAGE = "image";

    private static String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO + " (" + COLUMN_TODO_ID + " INTEGER PRIMARY KEY, " + COLUMN_TODO + " TEXT NOT NULL, " + COLUMN_IMAGE + " BLOB NOT NULL " + ")" ;

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private static ToDoListDBAdapter toDoListDBAdapterInstance;

    private ToDoListDBAdapter(Context context)
    {
        this.context = context;
        sqLiteDatabase = new ToDoListDBHelper(this.context, DB_NAME, null, DB_Version).getWritableDatabase();
    }

    public static ToDoListDBAdapter getToDoListDBAdapterInstance(Context context)
    {
        if(toDoListDBAdapterInstance == null)
        {
            toDoListDBAdapterInstance = new ToDoListDBAdapter(context);
        }
        return toDoListDBAdapterInstance;
    }

    public boolean insert(String toDoItem, byte[] image)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, toDoItem);
        contentValues.put(COLUMN_IMAGE, image);
        return sqLiteDatabase.insert(TABLE_TODO, null, contentValues) > 0;
    }

    public boolean insertImage(String toDoItem, byte[] image)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, toDoItem);
        contentValues.put(COLUMN_IMAGE, image);

        return sqLiteDatabase.insert(TABLE_TODO, null, contentValues) > 0;
    }

    public boolean delete(int taskId)
    {
           return sqLiteDatabase.delete(TABLE_TODO, COLUMN_TODO_ID + " = " + taskId, null)>0;
    }

    public boolean modify(int taskId, String newToDoItem)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, newToDoItem);
        return sqLiteDatabase.update(TABLE_TODO, contentValues, COLUMN_TODO_ID + "=" + taskId, null) > 0;
    }

//    public List<ImageRequest> getAllToDos(){  //Fetch data from SQLite
//
//        List<ImageRequest> toDOList = new ArrayList<>();
//        Cursor cursor = sqLiteDatabase.query(TABLE_TODO, new String[] {COLUMN_TODO_ID, COLUMN_TODO, COLUMN_IMAGE }, null, null, null, null, null, null);
//
//        if(cursor != null & cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                ImageRequest imageRequest = new ImageRequest(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2));
//                toDOList.add(imageRequest);
//            }
//            return toDOList;
//        }
//        return null;
//    }

    private static class ToDoListDBHelper extends SQLiteOpenHelper{

        public ToDoListDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_TODO);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
            onCreate(db);
        }
    }
}
