package com.example.schoolapp.ui_student.main_drawer;

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

public class main_drawer extends Fragment {

    private main_drawerViewModel main_drawerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        main_drawerViewModel =
                ViewModelProviders.of(this).get(main_drawerViewModel.class);
        View root = inflater.inflate(R.layout.nav_header_main_student, container, false);

        //final TextView title_email = root.findViewById(R.id.title_email);
        /*final TextView title_name = root.findViewById(R.id.title_name);


        main_drawerViewModel.getText_title_name().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                title_name.setText(s);

            }
        });*/
        return root;
    }



}
