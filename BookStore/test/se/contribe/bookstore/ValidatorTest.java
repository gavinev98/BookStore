package se.contribe.bookstore;

import junit.framework.TestCase;
import se.contribe.bookstore.convertor.BigDecimalConvertor;

public class ValidatorTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testValidateSearchString() {
		
		assertTrue(Validator.validateSearchString("author:Leo Tolstoy"));
		assertTrue(Validator.validateSearchString("title:Jane Eyre"));
	}

	public void testIsMenuCommandCorrect() {
		assertFalse(Validator.isMenuCommandCorrect("a"));
		
		assertTrue(Validator.isMenuCommandCorrect("0"));
		assertTrue(Validator.isMenuCommandCorrect("1"));
		assertTrue(Validator.isMenuCommandCorrect("6"));
		
		assertFalse(Validator.isMenuCommandCorrect("7"));
	}

	public void testIsValidBigDecimal() {
		assertTrue(Validator.isValidBigDecimal("120.3"));
		
		assertTrue(Validator.isValidBigDecimal(BigDecimalConvertor.toBigDecimal("1,320.3").toString()));
		
		assertFalse(Validator.isValidBigDecimal("a"));
		
	}

	public void testIsValidInt() {
		assertTrue(Validator.isValidInt("20"));
		
		assertFalse(Validator.isValidInt("a"));
	}

	public void testIsBuyMenuCommandCorrect() {
		assertTrue(Validator.isBuyMenuCommandCorrect("0"));
		assertTrue(Validator.isBuyMenuCommandCorrect("4"));
		
		assertFalse(Validator.isBuyMenuCommandCorrect("a"));
		assertFalse(Validator.isBuyMenuCommandCorrect("5"));
		assertFalse(Validator.isBuyMenuCommandCorrect("-1"));
	}

}
