package io.pw;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by pwykowski
 */
public class StreamExample {
	public static void main(String[] args) {
		final List<String> names = List.of("John", "Marry", "George", "Paul", "Ann", "Alice", "Bob");
		final Stream<String> stream = names.stream()
				.filter(s -> {
					System.out.println(s);
					return s.startsWith("A");
				})
				.map(String::toUpperCase)
				.sorted();

		System.out.println("Stream nie zostanie wywołany");

		final List<String> filteredNames = stream.toList();
		filteredNames.stream();
	}
}
