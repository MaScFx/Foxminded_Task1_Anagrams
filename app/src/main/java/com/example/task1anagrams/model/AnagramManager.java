package com.example.task1anagrams.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AnagramManager {
    private String input;
    private Set<Character> ignoreSet;

    public void setInput(String input) {
        if (input == null) {
            this.input = "";
        }
        this.input = input;
    }

    public void setIgnoreSet(Set<Character> ignoreSet) {
        if (ignoreSet == null) {
            this.ignoreSet = new HashSet<>();
        }
        this.ignoreSet = ignoreSet;
    }

    public AnagramManager(String input, Set<Character> ignoreSet) {
        this.input = input;
        this.ignoreSet = ignoreSet;
    }

    public String getReverseWords() {
        String output = "";
        for (String word : input.split(" ")) {
            output = String.join(" ", output, reverseWord(word.toCharArray(), ignoreSet));
        }
        return output;
    }

    private String reverseWord(char[] input, Set<Character> ignore) {
        ArrayList<Integer> ignoreChar = new ArrayList<>();
        ArrayList<Integer> reverseChar = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            if (ignore.contains(input[i])) {
                ignoreChar.add(i);
            } else {
                reverseChar.add(i);
            }
        }

        String output = "";
        for (int i = 0; i < input.length; i++) {
            if (ignoreChar.contains(i)) {
                output = String.join("", output, String.valueOf(input[i]));
            } else {
                output = String.join("", output,
                        String.valueOf(input[reverseChar.remove(reverseChar.size() - 1)]));
            }
        }

        return output;
    }
}
