package com.example.schoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by he on 2016/10/1.
 */
public class eventAdapter_student extends ArrayAdapter<Info> {
    private int resourceId;

    public eventAdapter_student(Context context, int resource, List<Info> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info student = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.student_first_name_admin = (TextView) view.findViewById(R.id.short_description_table_student);
            viewHolder.student_family_name_admin = (TextView) view.findViewById(R.id.date_table_student);


            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.student_first_name_admin.setText(student.gettitle());
        viewHolder.student_family_name_admin.setText(student.getdate());

        return view;

    }

    class ViewHolder {
        TextView student_first_name_admin;
        TextView student_family_name_admin;

    }

}

