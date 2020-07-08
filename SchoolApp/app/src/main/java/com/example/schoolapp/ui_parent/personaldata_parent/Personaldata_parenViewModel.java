package com.example.schoolapp.ui_parent.personaldata_parent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.schoolapp.MainActivity_Parent;
import com.example.schoolapp.MainActivity_Student;

public class Personaldata_parenViewModel extends ViewModel {

    private MutableLiveData<String> first_name, family_name, email, number, role;

    public Personaldata_parenViewModel() {

        first_name = new MutableLiveData<>();
        first_name.setValue(MainActivity_Parent.text_firstname_pa);

        family_name = new MutableLiveData<>();
        family_name.setValue(MainActivity_Parent.text_familyname_pa);

        email = new MutableLiveData<>();
        email.setValue(MainActivity_Parent.text_email_pa);

        number = new MutableLiveData<>();
        number.setValue(MainActivity_Parent.text_number_pa);

        role = new MutableLiveData<>();
        role.setValue(MainActivity_Parent.text_role_pa);

    }

    public LiveData<String> getText_first_name_pa() {
        return first_name;
    }

    public LiveData<String> getText_email_pa() {
        return email;
    }

    public LiveData<String> getText_family_name_pa() {
        return family_name;
    }

    public LiveData<String> getText_number_pa() {
        return number;
    }

    public LiveData<String> getText_role_pa() {
        return role;
    }
}