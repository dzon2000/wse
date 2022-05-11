package io.pw.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AccountAspect {

	@Before("execution (boolean withdraw(int))")
	public void callWithdraw(JoinPoint jp) {
		System.out.println("Withdraw called...");
	}

}
