package com.example.myrecipeapp.ui.saverecipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myrecipeapp.databinding.FragmentSaveRecipesBinding;

public class SaveRecipesFragment extends Fragment {

    private FragmentSaveRecipesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SaveRecipesViewModel saveRecipesViewModel =
                new ViewModelProvider(this).get(SaveRecipesViewModel.class);

        binding = FragmentSaveRecipesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSaverecipes;
        saveRecipesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}