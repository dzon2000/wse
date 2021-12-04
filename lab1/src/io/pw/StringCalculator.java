package io.pw;

/**
 * Created by pwykowski
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Create a simple String calculator with a method signature:
 * ———————————————
 * int Add(string numbers)
 * ———————————————
 * 1. The method can take up to two numbers, separated by commas, and will return their sum.
 * for example “” or “1” or “1,2” as inputs.
 * (for an empty string it will return 0)
 * <p>
 * 2. Allow the Add method to handle an unknown amount of numbers
 * <p>
 * 3. Allow the Add method to handle new lines between numbers (instead of commas).
 * - the following input is ok: “1\n2,3” (will equal 6)
 * - the following input is NOT ok: “1,\n” (not need to prove it - just clarifying)
 * <p>
 * 4. Support different delimiters
 * - to change a delimiter, the beginning of the string will contain a separate line that looks like this: “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
 * - the first line is optional. all existing scenarios should still be supported
 * <p>
 * 5. Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was passed.
 * if there are multiple negatives, show all of them in the exception message.
 */
public class StringCalculator {

	private static final String REGEX = "[\n,]";

	int add(String numbers) {
		String regex = REGEX;
		if (numbers.startsWith("//")) {
			regex = getCustomRegex(numbers);
			numbers = numbers.substring(4);
		}
		if (numbers.isEmpty()) {
			return 0;
		}
		final String[] split = numbers.split(regex);
		return sum(split);
	}

	private int sum(String[] split) {
		int sum = 0;
		List<Integer> negativeNumbers = new ArrayList<>();
		for (String s : split) {
			final int number = parseNumber(s);
			if (number < 0) {
				negativeNumbers.add(number);
			}
			sum += number;
		}
		if (!negativeNumbers.isEmpty()) {
			throw new NumberFormatException("Negative numbers: " + getNegativeNumbers(negativeNumbers));
		}
		return sum;
	}

	private String getNegativeNumbers(List<Integer> negativeNumbers) {
		return negativeNumbers.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(", "));
	}

	private int parseNumber(String s) {
		return Integer.parseInt(s);
	}

	private String getCustomRegex(String numbers) {
		String delimiter = String.valueOf(numbers.charAt(2));
		return REGEX.substring(0,1) + delimiter + REGEX.substring(1);
	}

}
