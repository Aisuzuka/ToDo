package com.example.pyakuren_chienhua.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Create extends AppCompatActivity {
    EditText title, description;
    Button dateButton, create, cancle;
    RadioGroup rg;
    TextView dateView;
    int mYear, mMonth, mDay, mHour, mMin;
    String priority;
    StringBuffer stringBuffer = new StringBuffer("");
    ToDoFormat toDoFormat = new ToDoFormat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        initView();
        initOnClickListener();
    }

    private void initOnClickListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioButton:
                        priority = "1";
                        break;
                    case R.id.radioButton2:
                        priority = "2";
                        break;
                    case R.id.radioButton3:
                        priority = "3";
                        break;
                }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create.this.finish();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDoFormat.title = title.getText().toString();
                toDoFormat.date = stringBuffer.toString();
                toDoFormat.description = description.getText().toString();
                toDoFormat.priority = priority;
                if(checkNull()) {
                    MyDatabase myDatabase = new MyDatabase(Create.this, "main", null, 1);
                    SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("title", toDoFormat.title);
                    contentValues.put("date", toDoFormat.date);
                    contentValues.put("description", toDoFormat.description);
                    contentValues.put("priority", toDoFormat.priority);
                    sqLiteDatabase.insert("main", null, contentValues);
                    sqLiteDatabase.close();
                    Create.this.finish();
                } else {
                    Toast.makeText(Create.this, "資料請確實輸入", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMin = c.get(Calendar.MINUTE);
                stringBuffer.setLength(0);
                new DatePickerDialog(Create.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        stringBuffer.append(String.valueOf(year*10000 + month*100 + dayOfMonth));
                        new TimePickerDialog(Create.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                stringBuffer.append(" " + String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
                                dateView.setText(stringBuffer.toString());
                            }
                        }, mHour, mMin, true).show();
                    }
                }, mYear, mMonth, mDay).show();
            }
        });
    }

    private boolean checkNull() {
        if(checkStringIllegule(toDoFormat.description))
            return false;
        else if(checkStringIllegule(toDoFormat.priority))
            return false;
        else if(checkStringIllegule(toDoFormat.date))
            return false;
        else if(checkStringIllegule(toDoFormat.title))
            return false;
        return true;
    }

    private boolean checkStringIllegule(String string) {
        if(string == "" || string == null)
            return true;
        else
            return false;
    }

    private void initView() {
        title = (EditText) findViewById(R.id.editText);
        description = (EditText) findViewById(R.id.editText2);
        dateButton = (Button) findViewById(R.id.button2);
        dateView = (TextView) findViewById(R.id.textView3);
        create = (Button) findViewById(R.id.button3);
        cancle = (Button) findViewById(R.id.button4);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
    }
}
