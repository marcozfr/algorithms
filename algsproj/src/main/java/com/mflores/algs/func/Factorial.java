package com.mflores.algs.func;

import java.math.BigDecimal;

public class Factorial {
	
	public static BigDecimal getFactorial(BigDecimal val) {
		if(val.equals(BigDecimal.ZERO)) return val;
		if(val.equals(BigDecimal.ONE)) return val;
		else return val.multiply(getFactorial(val.subtract(BigDecimal.ONE)));
	}

}
