package com.example.schoolapp.ui_parent.children_parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Children_parentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Children_parentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}