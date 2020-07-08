package com.example.schoolapp.ui_student.main_drawer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class main_drawerViewModel extends ViewModel {

    private MutableLiveData<String> title_name;

    public main_drawerViewModel() {

        title_name = new MutableLiveData<>();
        //title_name.setValue(MainActivity_Student.title_name);
    }

    public LiveData<String> getText_title_name() {
        return title_name;
    }
}