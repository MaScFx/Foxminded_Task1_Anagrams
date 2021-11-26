package com.example.task1anagrams.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.task1anagrams.R;
import com.example.task1anagrams.databinding.FragmentHomeBinding;
import com.example.task1anagrams.ui.SharedViewModel;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SharedViewModel sharedViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeEtTypeAWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (binding.homeEtTypeAWord.getText().length() > 0) {
                    binding.homeBtnConvert.setEnabled(true);
                    binding.homeBtnConvert.setTextColor(getResources().getColor(R.color.white));
                } else {
                    binding.homeBtnConvert.setEnabled(false);
                    binding.homeBtnConvert.setTextColor(getResources().getColor(R.color.gray));

                }
            }
        });


        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> binding.homeTvOutputText.setText(s));
        sharedViewModel.getIgnore().observe(getViewLifecycleOwner(),
                s -> homeViewModel.setIgnore(s));

        binding.homeBtnConvert.setOnClickListener(v -> {
            homeViewModel.setText(binding.homeEtTypeAWord.getText().toString());
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}