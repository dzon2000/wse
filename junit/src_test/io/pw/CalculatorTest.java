package io.pw;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by pwykowski
 */
class CalculatorTest {

	@Test
	public void shouldReturn50For20And30() {
		// given
		Calculator calculator = new Calculator();
		// when
		final int result = calculator.calculate(20, 30);
		// then
		assertEquals(51, result);
	}

}