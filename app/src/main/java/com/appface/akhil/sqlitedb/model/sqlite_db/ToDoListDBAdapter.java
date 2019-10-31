package com.appface.akhil.sqlitedb.model.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.appface.akhil.sqlitedb.beans.ToDO;

import java.util.ArrayList;
import java.util.List;

public class ToDoListDBAdapter {

    private static final String TAG = "ToDoListDBAdapter";

    private static final String DB_NAME = "todolist.db";
    private static final int DB_Version = 4;

    private static final String TABLE_TODO = "table_todo";
    private static final String COLUMN_TODO_ID = "task_id";
    private static final String COLUMN_TODO = "todo";

    private static String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO + " (" + COLUMN_TODO_ID + " INTEGER PRIMARY KEY, " + COLUMN_TODO + " TEXT NOT NULL )";

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

    public boolean insert(String toDoItem)
    {
        Log.d(TAG, "insert: " + toDoItem);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, toDoItem);
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

    public List<ToDO> getAllToDos(){

        List<ToDO> toDOList = new ArrayList<ToDO>();
        Cursor cursor = sqLiteDatabase.query(TABLE_TODO, new String[] {COLUMN_TODO_ID, COLUMN_TODO}, null, null, null, null, null, null);

        if(cursor != null & cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                ToDO toDO = new ToDO(cursor.getInt(0), cursor.getString(1));
                toDOList.add(toDO);
            }
            return toDOList;
        }
        else
        return null;
    }

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

        }
    }




}
