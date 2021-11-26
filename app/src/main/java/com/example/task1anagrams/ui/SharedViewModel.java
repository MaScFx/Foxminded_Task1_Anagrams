package com.example.task1anagrams.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<String> ignore = new MutableLiveData<>();

    public void setText(String text) {
        ignore.setValue(text);
    }

    public MutableLiveData<String> getIgnore() {
        return ignore;
    }

}
