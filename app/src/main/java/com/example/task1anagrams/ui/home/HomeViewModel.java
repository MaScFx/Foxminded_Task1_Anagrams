package com.example.task1anagrams.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task1anagrams.model.AnagramManager;

import java.util.HashSet;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final AnagramManager anagramManager;
    private final HashSet<Character> ignore;


    public HomeViewModel() {
        mText = new MutableLiveData<>();
        ignore = new HashSet<>();
        anagramManager = new AnagramManager("",ignore);
        mText.setValue(anagramManager.getReverseWords());
    }

    public void setIgnore(String inputIgnore) {
        char[] charArray = inputIgnore.toCharArray();
        ignore.clear();
        for (char c : charArray) {
            ignore.add(c);
        }
        anagramManager.setIgnoreSet(ignore);
        anagramNotifySetChanges();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        mText.setValue(text);
        anagramManager.setInput(text);
        anagramNotifySetChanges();
    }
    private void anagramNotifySetChanges(){
        mText.setValue(anagramManager.getReverseWords());
    }
}