package se.contribe.bookstore.convertor;

import junit.framework.TestCase;
import se.contribe.bookstore.Validator;

public class BigDecimalConvertorTest extends TestCase {

	public void testToBigDecimal(){
		assertTrue(Validator.isValidBigDecimal(BigDecimalConvertor.toBigDecimal("2,499,99").toString()));
	}
	
	

}
