package com.example.myrecipeapp.ui.savedrecipes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedRecipesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SavedRecipesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is saved recipes fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}