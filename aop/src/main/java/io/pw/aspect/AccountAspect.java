package io.pw.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AccountAspect {

	private static final Logger logger = LogManager.getLogger(Account.class);

	@Before("execution (boolean withdraw(int))")
	public void callWithdraw(JoinPoint jp) {
		logger.trace("Withdraw called with input parameter: {}", jp.getArgs()[0]);
	}

}
