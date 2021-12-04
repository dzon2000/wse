package io.pw;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by pwykowski
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {

	private Integer number = ThreadLocalRandom.current().nextInt();

	@Test
	public void shouldReturn50For20And30() {
		System.out.println(">>shouldReturn50For20And30>>" + number);
		// given
		Calculator calculator = new Calculator();
		// when
		final int result = calculator.calculate(20, 30);
		// then
		assertEquals(50, result);
	}

	@Test
	public void shouldReturn30For10And20() {
		System.out.println(">>shouldReturn30For10And20>>" + number);
		// given
		Calculator calculator = new Calculator();
		// when
		final int result = calculator.calculate(10, 20);
		// then
		assertEquals(30, result);
	}

	@ParameterizedTest
	@CsvSource({
			"10,20,30",
			"20,30,50",
			"1,2,4"
	})
	@Tags({
			@Tag("parametrized"),
			@Tag("calculate"),
	})
	public void parameterizedTest(int a, int b, int expected) {
		System.out.println(">>shouldReturn30For10And20>>" + number);
		// given
		Calculator calculator = new Calculator();
		// when
		final int result = calculator.calculate(a, b);
		// then
		assertEquals(expected, result, "Nie udało się...");
	}

	@Test
	public void shouldThrowNumberFormatException() {
		// given
		Calculator calculator = new Calculator();
		// when

		// then
		assertThrows(NumberFormatException.class, () -> calculator.parse("aa"));
	}

}