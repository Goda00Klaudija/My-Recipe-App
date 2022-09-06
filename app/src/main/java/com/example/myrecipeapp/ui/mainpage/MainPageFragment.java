package com.example.myrecipeapp.ui.mainpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myrecipeapp.AllRecipeSearch;
import com.example.myrecipeapp.MainPage;
import com.example.myrecipeapp.MealCategories;
import com.example.myrecipeapp.R;
import com.example.myrecipeapp.RecipeDetailsActivity;
import com.example.myrecipeapp.databinding.ActivityMainPageBinding;

public class MainPageFragment extends Fragment {
    private ActivityMainPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MainPageViewModel mainPageViewModel =
                new ViewModelProvider(this).get(MainPageViewModel.class);

        binding = ActivityMainPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}