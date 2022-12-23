package io.pw;

import io.pw.db.ProductRepository;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pwykowski
 */
public class H2Database {

	private static final String[] BOMBKI = {"\u001B[31m@\u001B[0m", "\u001B[35m$\u001B[0m", "\u001B[34m+\u001B[0m"};

	public static void main(String[] args) {

		final int height = 10;
		for (int i = 0; i < height; i++) {
			for (int j = height - i; j >= 0; j--) {
				System.out.print(" ");
			}
			for (int j = 0; j < 2*i + 1; j++) {
				if (Math.random() < 0.3) {
					System.out.print(BOMBKI[ThreadLocalRandom.current().nextInt(3)]);
				} else
					System.out.print("\u001B[32m*\u001B[0m");
			}
			System.out.println();
		}
		for (int j = height - 1; j >= 0; j--) {
			System.out.print(" ");
		}
		System.out.println("\u001B[30m|||");

	}

}
