package com.example.schoolapp.ui_student.noticeboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.schoolapp.R;

public class NoticeboardFragment extends Fragment {

    private NoticeboardViewModel NoticeboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NoticeboardViewModel =
                ViewModelProviders.of(this).get(NoticeboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_noticeboard, container, false);
        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}