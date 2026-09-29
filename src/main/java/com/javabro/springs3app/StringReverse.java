package com.javabro.springs3app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringReverse {
    public static void main(String[] args) {
        String s = "Hello World";

        List<String> chars = new ArrayList<>();

        Arrays.stream(s.split("")).forEach(s1 -> chars.add(0, s1));
        System.out.println(chars.stream().collect(Collectors.joining("")));
    }
}
