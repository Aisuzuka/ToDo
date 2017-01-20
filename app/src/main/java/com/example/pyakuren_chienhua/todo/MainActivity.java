package com.example.pyakuren_chienhua.todo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
        loadToDo();
        initAdapter();
        initOnClickListener();
    }

    private void initOnClickListener() {
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initAdapter() {
        toDoAdapter = new ToDoAdapter(this, R.layout.item_todo, toDoList);
        toDoListView.setAdapter(toDoAdapter);
        toDoAdapter.notifyDataSetChanged();
    }

    private void loadToDo() {
        ToDoFormat item = new ToDoFormat();
        item.title = "title";
        item.description = "des";
        item.date = "03:13";
        item.priority = 1;
        toDoList.add(item);
    }

    private void initView() {
        create = (Button) findViewById(R.id.newToDo);
        toDoListView = (ListView) findViewById(R.id.toDoAdapter);
    }
}
