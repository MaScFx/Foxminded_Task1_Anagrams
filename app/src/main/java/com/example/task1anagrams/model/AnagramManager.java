package com.example.task1anagrams.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AnagramManager {
    private String input;
    private Set<Character> ignoreSet;

    public void setInput(String input) {
        this.input = input;
    }

    public void setIgnoreSet(Set<Character> ignoreSet) {
        this.ignoreSet = ignoreSet;
    }

    public AnagramManager(String input, Set<Character> ignoreSet) {
        this.input = input;
        this.ignoreSet = ignoreSet;
    }

    public String getReverseWords() {
        if (ignoreSet == null || ignoreSet.size() == 0) {
            return listToString(Arrays.stream(input.split(" "))
                    .map(word -> new StringBuilder(word).reverse().toString())
                    .collect(Collectors.toList()));
        }

        List<Character[]> inputWordsWithNulls = Arrays.stream(input.split(" "))
                .map(s -> changeTargetCharToNull(new String(s.toCharArray()).chars()
                        .mapToObj(c -> (char) c)
                        .toArray(Character[]::new), ignoreSet))
                .collect(Collectors.toList());

        List<List<Character>> inputCharsToReverse = Arrays.stream(input.split(" "))
                .map(s -> getCharsToReverse(new String(s.toCharArray()).chars()
                        .mapToObj(c -> (char) c)
                        .toArray(Character[]::new), ignoreSet))
                .collect(Collectors.toList());

        assert inputCharsToReverse.size() == inputWordsWithNulls.size();
        List<String> output = new ArrayList<>();

        for (int i = 0; i < inputWordsWithNulls.size(); i++) {
            List<Character> reverse = inputCharsToReverse.get(i);
            Collections.reverse(reverse);
            output.add(injectRequiredCharsToCharArray(inputWordsWithNulls.get(i), reverse));
        }

        return listToString(output);
    }

    private List<Character> getCharsToReverse(Character[] input, Set<Character> ignore) {
        return Arrays.stream(input)
                .filter(c -> !ignore.contains(c))
                .collect(Collectors.toList());
    }

    private Character[] changeTargetCharToNull(Character[] input, Set<Character> ignore) {
        return Arrays.stream(input)
                .map(c -> !ignore.contains(c) ? null : c)
                .toArray(Character[]::new);
    }

    private String injectRequiredCharsToCharArray(Character[] wordWithNulls, List<Character> requiredCharacters) {
        assert countNullsInArray(wordWithNulls) == requiredCharacters.size();

        for (int i = 0; i < wordWithNulls.length; i++) {
            if (wordWithNulls[i] == null) {
                wordWithNulls[i] = requiredCharacters.remove(0);
            }
        }
        return arrayToString(wordWithNulls);
    }

    private Integer countNullsInArray(Character[] arrayWithNulls) {
        return (int) Arrays.stream(arrayWithNulls)
                .filter(Objects::isNull)
                .count();
    }

    private String listToString(List<String> input) {
        StringBuilder output = new StringBuilder();
        input.stream()
                .map(String::valueOf)
                .forEach(t -> output.append(t).append(" "));
        if (output.length() > 0) {
            output.deleteCharAt(output.length() - 1);
        }
        return output.toString();
    }

    private String arrayToString(Object[] input) {
        StringBuilder output = new StringBuilder();
        for (Object i : input) {
            output.append(i.toString());
        }
        return output.toString();
    }

}


