package com.lottery.lottery.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGeneratorUtil {
    public static String generateSequence() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) digits.add(i);

        Collections.shuffle(digits);

        int d1 = digits.get(0);
        int d2 = digits.get(1);
        int d3 = digits.get(2);
        int d4 = digits.get(3);

        char letter = (char) ('A' + new Random().nextInt(26));

        return d1 + " " + d2 + " " + d3 + " " + d4 + " " + letter;
    }
}
