package com.example.myrecipeapp.ui.mainpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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