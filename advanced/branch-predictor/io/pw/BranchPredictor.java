package io.pw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BranchPredictor {
    public static void main(String[] args) {
        final List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 32768; i++) {
            numbers.add(ThreadLocalRandom.current().nextInt(0, 256));
        }
        Collections.sort(numbers);
        List<Integer> result = new ArrayList<>();
        long sum = 0;
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            for (Integer number : numbers) {
                if (number < 128) {
                    sum += number;
                }
            }
        }
        System.out.println("Finished in: " + (System.currentTimeMillis() - start) / 1000.f + " s");
    }
}
