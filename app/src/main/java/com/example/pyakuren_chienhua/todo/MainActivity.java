package com.example.pyakuren_chienhua.todo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button create;
    private ListView toDoListView;
    private ArrayList<ToDoFormat> toDoList = new ArrayList<ToDoFormat>();
    private ArrayAdapter<ToDoFormat> toDoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initAdapter();
        initOnClickListener();
    }

    private void initOnClickListener() {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Create.class);
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        toDoAdapter = new ToDoAdapter(this, R.layout.item_todo, toDoList);
        toDoListView.setAdapter(toDoAdapter);
        toDoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadToDo();
    }

    private void loadToDo() {
        toDoList.clear();
        MyDatabase myDatabase = new MyDatabase(this, "main", null, 1);
        SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from main", null);
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            ToDoFormat item = new ToDoFormat();
            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
            item.title = cursor.getString(cursor.getColumnIndex("title"));
            item.description = cursor.getString(cursor.getColumnIndex("description"));
            item.date = cursor.getString(cursor.getColumnIndex("date"));
            item.priority = cursor.getString(cursor.getColumnIndex("priority"));
            toDoList.add(item);
        }
        sqLiteDatabase.close();
        toDoAdapter.notifyDataSetChanged();
    }

    private void initView() {
        create = (Button) findViewById(R.id.newToDo);
        toDoListView = (ListView) findViewById(R.id.toDoAdapter);
    }
}
