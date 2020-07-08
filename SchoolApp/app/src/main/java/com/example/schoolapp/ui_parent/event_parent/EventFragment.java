package com.example.schoolapp.ui_parent.event_parent;

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

public class EventFragment extends Fragment {

    private Event_parentViewModel Event_parentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Event_parentViewModel =
                ViewModelProviders.of(this).get(Event_parentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_event_parent, container, false);
        /*final TextView textView = root.findViewById(R.id.text_slideshow);
        Event_parentViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}