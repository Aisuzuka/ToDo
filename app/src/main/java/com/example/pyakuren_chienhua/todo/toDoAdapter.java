package com.example.pyakuren_chienhua.todo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pyakuren-Chienhua on 2017/1/19.
 */

public class ToDoAdapter extends ArrayAdapter<ToDoFormat> {
    ArrayList<ToDoFormat> toDoList;
    LayoutInflater inflater;
    ViewHolder viewHolder = null;

    public ToDoAdapter(Context context, int resource, ArrayList<ToDoFormat> toDoList) {
        super(context, resource, toDoList);
        this.toDoList = toDoList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_todo, null);
            viewHolder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.title),
                    (TextView) convertView.findViewById(R.id.date),
                    (TextView) convertView.findViewById(R.id.priority)
            );
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        setView(position);
        return convertView;
    }

    private void setView(int position) {
        viewHolder.title.setText(toDoList.get(position).title);
        viewHolder.date.setText(toDoList.get(position).date);
        viewHolder.priority.setText(String.valueOf(toDoList.get(position).priority));
    }

    private class ViewHolder {
        TextView title;
        TextView date;
        TextView priority;

        public ViewHolder(TextView title, TextView date, TextView priority) {
            this.title = title;
            this.date = date;
            this.priority = priority;
        }
    }
}
