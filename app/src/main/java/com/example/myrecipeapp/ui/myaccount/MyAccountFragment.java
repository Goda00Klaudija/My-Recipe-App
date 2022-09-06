package com.example.myrecipeapp.ui.myaccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myrecipeapp.databinding.ActivityMyAccountBinding;

public class MyAccountFragment extends Fragment {
    private ActivityMyAccountBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyAccountViewModel mainPageViewModel =
                new ViewModelProvider(this).get(MyAccountViewModel.class);

        binding = ActivityMyAccountBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}