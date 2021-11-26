package com.example.task1anagrams.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.task1anagrams.databinding.FragmentSettingsBinding;
import com.example.task1anagrams.ui.SharedViewModel;


public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private SharedViewModel sharedViewModel;
    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel.getIgnore().observe(getViewLifecycleOwner(),
                s -> settingsViewModel.setText(s));
        settingsViewModel.getText().observe(getViewLifecycleOwner(),
                s -> binding.settingEtIgnoredLetters.setText(s));

        binding.settingBtnSave.setOnClickListener(v -> {
            sharedViewModel.setText(binding.settingEtIgnoredLetters.getText().toString());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}