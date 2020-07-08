package com.example.schoolapp.ui_student.noticeboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoticeboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NoticeboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}