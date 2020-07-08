package com.example.schoolapp.ui_parent.logout_parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Logout_parentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Logout_parentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}