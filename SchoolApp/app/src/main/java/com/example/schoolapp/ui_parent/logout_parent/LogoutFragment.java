package com.example.schoolapp.ui_parent.logout_parent;

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

public class LogoutFragment extends Fragment {

    private Logout_parentViewModel Logout_parentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Logout_parentViewModel =
                ViewModelProviders.of(this).get(Logout_parentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_logout_parent, container, false);
        /*final TextView textView = root.findViewById(R.id.text_share);
        Logout_parentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}