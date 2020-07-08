package com.example.schoolapp.ui_parent.communication_parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Communication_parentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Communication_parentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}