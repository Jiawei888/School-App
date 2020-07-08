package com.example.schoolapp.ui_parent.event_parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Event_parentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Event_parentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}