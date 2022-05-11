package io.pw.aspect;

public class App {

	public static void main(String[] args) {
		Account ac = new Account();
		System.out.println(String.format("Was it successful? %s", ac.withdraw(5)));
	}

}
