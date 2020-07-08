package com.example.schoolapp.ui_parent.communication_parent;

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

public class CommunicationFragment extends Fragment {

    private Communication_parentViewModel Communication_parentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Communication_parentViewModel =
                ViewModelProviders.of(this).get(Communication_parentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_communication_parent, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        Communication_parentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}