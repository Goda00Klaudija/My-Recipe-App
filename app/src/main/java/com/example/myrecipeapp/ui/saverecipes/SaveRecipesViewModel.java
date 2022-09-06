package com.example.myrecipeapp.ui.saverecipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaveRecipesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SaveRecipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is saved recipes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}