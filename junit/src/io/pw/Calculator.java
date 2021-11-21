package io.pw;

/**
 * Created by pwykowski
 */
public class Calculator {

	int calculate(int a, int b) {
		if (a < 0) {
			return b + a;
		}
		return a + b;
	}

}
