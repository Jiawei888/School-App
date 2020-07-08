package com.example.schoolapp.ui_student.personaldata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.schoolapp.R;

public class PersonaldataFragment extends Fragment{

    private PersonaldataViewModel PersonaldataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PersonaldataViewModel =
                ViewModelProviders.of(this).get(PersonaldataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personaldata_student, container, false);

        final TextView firstname = root.findViewById(R.id.firstname);
        final TextView email = root.findViewById(R.id.email);
        final TextView familyname = root.findViewById(R.id.familyname);
        final TextView Class = root.findViewById(R.id.year);
        final TextView number = root.findViewById(R.id.number);
        final TextView role = root.findViewById(R.id.ROLE);

        PersonaldataViewModel.getText_first_name().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                firstname.setText(s);
            }
        });

        PersonaldataViewModel.getText_email().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                email.setText(s);

            }
        });

        PersonaldataViewModel.getText_family_name().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                familyname.setText(s);

            }
        });

        PersonaldataViewModel.getText_Class().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Class.setText(s);

            }
        });

        PersonaldataViewModel.getText_number().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                number.setText(s);

            }
        });

        PersonaldataViewModel.getText_role().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                role.setText(s);

            }
        });

        return root;
    }


}