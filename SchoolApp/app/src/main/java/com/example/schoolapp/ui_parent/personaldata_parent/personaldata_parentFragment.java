package com.example.schoolapp.ui_parent.personaldata_parent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.schoolapp.R;
import com.example.schoolapp.ui_student.personaldata.PersonaldataViewModel;

public class personaldata_parentFragment extends Fragment {

    private Personaldata_parenViewModel Personaldata_parenViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Personaldata_parenViewModel =
                ViewModelProviders.of(this).get(Personaldata_parenViewModel.class);
        View root = inflater.inflate(R.layout.fragment_personaldata_parent, container, false);
        final TextView firstname = root.findViewById(R.id.firstname);
        final TextView email = root.findViewById(R.id.email);
        final TextView familyname = root.findViewById(R.id.familyname);
        final TextView Class = root.findViewById(R.id.year);
        final TextView number = root.findViewById(R.id.number);
        final TextView role = root.findViewById(R.id.ROLE);

        Personaldata_parenViewModel.getText_first_name_pa().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                firstname.setText(s);
            }
        });

        Personaldata_parenViewModel.getText_email_pa().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                email.setText(s);

            }
        });

        Personaldata_parenViewModel.getText_family_name_pa().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                familyname.setText(s);

            }
        });

        Personaldata_parenViewModel.getText_number_pa().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                number.setText(s);

            }
        });

        Personaldata_parenViewModel.getText_role_pa().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                role.setText(s);

            }
        });

        return root;
    }
}