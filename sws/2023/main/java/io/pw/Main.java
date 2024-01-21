package io.pw;

/**
 * Created by pwykowski
 */
public class Main {

	public static void main(String[] args) {
		if (returnNull().equals("POST")) {
			System.out.println("POST");
		} else {
			System.out.println("Not POST");
		}
	}

	private static String returnNull() {
		return null;
	}

}
