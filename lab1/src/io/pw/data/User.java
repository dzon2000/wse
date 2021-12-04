package io.pw.data;

import java.util.Objects;

/**
 * Created by pwykowski
 */
public class User {

	private final String name;
	private final String email;
	private final int age;
	private final byte[] password;

	public User(String name, String email, int age, byte[] password) {
		this.name = name;
		this.email = email;
		this.age = age;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	public byte[] getPassword() {
		return password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return age == user.age && Objects.equals(name, user.name) && Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, email, age);
	}
}
