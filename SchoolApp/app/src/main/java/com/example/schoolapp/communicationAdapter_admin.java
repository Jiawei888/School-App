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
public class communicationAdapter_admin extends ArrayAdapter<Info> {
    private int resourceId;

    public communicationAdapter_admin(Context context, int resource, ArrayList<Info> objects) {
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
            viewHolder.student_first_name_admin = (TextView) view.findViewById(R.id.first_name_communication_admin);
            viewHolder.student_family_name_admin = (TextView) view.findViewById(R.id.family_name_communication_admin);
            viewHolder.student_email_admin = (TextView) view.findViewById(R.id.email_communication_admin);
            viewHolder.student_class_admin = (TextView) view.findViewById(R.id.identity_communication_admin);


            view.setTag(viewHolder);

        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.student_first_name_admin.setText(student.getFirstname());
        viewHolder.student_family_name_admin.setText(student.getFamilytname());
        viewHolder.student_email_admin.setText(student.getEmail());
        viewHolder.student_class_admin.setText(student.getRole());


        return view;

    }

    class ViewHolder {
        TextView student_first_name_admin;
        TextView student_family_name_admin;
        TextView student_email_admin;
        TextView student_class_admin;

    }

}

