package com.example.pyakuren_chienhua.todo;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ToDoItem extends AppCompatActivity {
    TextView title, description;
    TextView dateView, priority;
    Button homePage, edit, delete;
    ToDoFormat item = new ToDoFormat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item);
        initView();
        fillData();
        initOnClickListener();
    }

    private void initOnClickListener() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase myDatabase = new MyDatabase(ToDoItem.this, "main", null, 1);
                SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();
                sqLiteDatabase.delete("main", "_id= ?", new String[]{String.valueOf(item.id)});
                sqLiteDatabase.close();
                finish();
            }
        });

        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = ToDoItem.this.getIntent().getExtras();
                bundle.putString("Type", "Edit");
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(ToDoItem.this, Create.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void fillData() {
        Bundle bundle = this.getIntent().getExtras();
        item.id = bundle.getInt("id");
        item.title = bundle.getString("title");
        item.description = bundle.getString("description");
        item.date = bundle.getString("date");
        item.priority = bundle.getString("priority");

        title.setText(item.title);
        description.setText(item.description);
        dateView.setText(item.date);
        priority.setText(item.priority);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.editText);
        description = (TextView) findViewById(R.id.editText2);
        dateView = (TextView) findViewById(R.id.textView3);
        priority = (TextView) findViewById(R.id.textView4);
        homePage = (Button) findViewById(R.id.button);
        edit = (Button) findViewById(R.id.button5);
        delete = (Button) findViewById(R.id.button6);
    }
}
