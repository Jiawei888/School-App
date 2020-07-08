package com.example.schoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.schoolapp.R;
import com.example.schoolapp.User;

import java.util.List;

/**
 * Created by he on 2016/10/1.
 */
public class StudentAdapter_parent extends ArrayAdapter<Parent> {
    private int resourceId;

    public StudentAdapter_parent(Context context, int resource, List<Parent> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Parent student = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.student_first_name = (TextView) view.findViewById(R.id.first_name_table);
            viewHolder.student_family_name = (TextView) view.findViewById(R.id.family_name_table);
            viewHolder.student_email = (TextView) view.findViewById(R.id.email_table);
            viewHolder.student_class = (TextView) view.findViewById(R.id.class_table);

            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.student_first_name.setText(student.getFirstname());
        viewHolder.student_family_name.setText(student.getFamilytname());
        viewHolder.student_email.setText(student.getName());
        viewHolder.student_class.setText(student.getclass());

        return view;

    }

    class ViewHolder {
        TextView student_first_name;
        TextView student_family_name;
        TextView student_email;
        TextView student_class;

    }

}
