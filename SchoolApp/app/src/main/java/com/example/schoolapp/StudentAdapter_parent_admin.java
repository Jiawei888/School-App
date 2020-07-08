package com.example.schoolapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by he on 2016/10/1.
 */
public class StudentAdapter_parent_admin extends ArrayAdapter<User> {
    private int resourceId;

    public StudentAdapter_parent_admin(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User student = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.student_first_name_admin = (TextView) view.findViewById(R.id.first_name_parent_admin);
            viewHolder.student_family_name_admin = (TextView) view.findViewById(R.id.family_name_parent_admin);
            viewHolder.student_email_admin = (TextView) view.findViewById(R.id.email_parent_admin);



            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.student_first_name_admin.setText(student.getFirstname());
        viewHolder.student_family_name_admin.setText(student.getFamilytname());
        viewHolder.student_email_admin.setText(student.getName());


        return view;

    }

    class ViewHolder {
        TextView student_first_name_admin;
        TextView student_family_name_admin;
        TextView student_email_admin;


    }

}

