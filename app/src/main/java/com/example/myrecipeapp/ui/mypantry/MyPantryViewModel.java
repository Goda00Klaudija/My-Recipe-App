package com.example.myrecipeapp.ui.mypantry;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyPantryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MyPantryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is my pantry fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}