package io.pw.data;

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
}
