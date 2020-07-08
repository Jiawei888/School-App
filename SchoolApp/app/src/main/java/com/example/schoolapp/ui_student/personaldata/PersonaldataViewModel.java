package com.example.schoolapp.ui_student.personaldata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schoolapp.MainActivity_Student;

public class PersonaldataViewModel extends ViewModel {

    private MutableLiveData<String> first_name, family_name, email, Class, number, role;

    public PersonaldataViewModel() {

        first_name = new MutableLiveData<>();
        first_name.setValue(MainActivity_Student.text_firstname_st);

        family_name = new MutableLiveData<>();
        family_name.setValue(MainActivity_Student.text_familyname_st);

        email = new MutableLiveData<>();
        email.setValue(MainActivity_Student.text_email_st);

        Class = new MutableLiveData<>();
        Class.setValue(MainActivity_Student.text_Class_st);

        number = new MutableLiveData<>();
        number.setValue(MainActivity_Student.text_number_st);

        role = new MutableLiveData<>();
        role.setValue(MainActivity_Student.text_role_st);

    }

    public LiveData<String> getText_first_name() {
        return first_name;
    }

    public LiveData<String> getText_email() {
        return email;
    }

    public LiveData<String> getText_family_name() {
        return family_name;
    }

    public LiveData<String> getText_Class() {
        return Class;
    }

    public LiveData<String> getText_number() {
        return number;
    }

    public LiveData<String> getText_role() {
        return role;
    }



}