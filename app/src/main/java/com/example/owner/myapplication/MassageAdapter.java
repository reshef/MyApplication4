package com.example.owner.myapplication;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


public class MassageAdapter extends ArrayAdapter<Massage> {

    public MassageAdapter(Context context, ArrayList<Massage> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.massage, parent, false);
        }

        convertView.setBackgroundColor(position % 2 == 1 ? Color.BLUE : Color.RED);

        Massage massage = getItem(position);

        TextView taskText = (TextView) convertView.findViewById(R.id.task_text);

        taskText.setText(massage.toString());

        // Return the completed view to render on screen
        return convertView;
    }
}