package io.pw;

import io.pw.data.User;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Created by pwykowski
 */
public class AssertionTest {

	@Test
	public void shouldAssertSameObjects() {
		// given
		User u1 = new User("John", "john@gmail.com", 77, "asdf".getBytes(StandardCharsets.UTF_8));
		User u2 = new User("John", "john@gmail.com", 77, "asdf".getBytes(StandardCharsets.UTF_8));
		final List<User> users = List.of(u1, u2);
		// when

		// then
		assertSame(u1, users.get(0));
	}

}
