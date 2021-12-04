package io.pw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Created by pwykowski
 */
class StringCalculatorTest {

	private final StringCalculator calculator = new StringCalculator();

	@Test
	public void shouldReturn0ForEmptyString() {
		// given
		// when
		final int result = calculator.add("");
		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnSameNumberForSingleString() {
		// given
		// when
		final int result = calculator.add("1");
		// then
		assertEquals(1, result);
	}

	@Test
	public void shouldReturnSumFor2Strings() {
		// given
		// when
		final int result = calculator.add("1,2");
		// then
		assertEquals(3, result);
	}

	@ParameterizedTest
	@MethodSource("getNumbers")
	public void shouldReturnSumForMultipleNumbers(String numbers, int expected) {
		// given
		// when
		final int result = calculator.add(numbers);
		// then
		assertEquals(expected, result);
	}

	@Test
	public void shouldReturnAllowNewLineSeparator() {
		// given
		// when
		final int result = calculator.add("1\n2,3");
		// then
		assertEquals(6, result);
	}

	@Test
	public void shouldReturn3ForCustomDelimiter() {
		// given
		// when
		final int result = calculator.add("//;\n1;2");
		// then
		assertEquals(3, result);
	}

	@Test
	public void shouldThrowExceptionForNegativeNumber() {
		// given
		// when
		// then
		assertThrows(NumberFormatException.class, () -> calculator.add("-1"));
	}

	@Test
	public void shouldThrowExceptionForMultipleNegativeNumbers() {
		// given
		// when
		// then
		final NumberFormatException exc = assertThrows(NumberFormatException.class, () -> calculator.add("-1,-4,6"));
		assertEquals("Negative numbers: -1, -4", exc.getMessage());
	}

	private static Stream<Arguments> getNumbers() {
		return Stream.of(
				arguments("1,2,3", 6),
				arguments("1,2,3,4", 10),
				arguments("5,2,1,4,7,8", 27)
		);
	}

}