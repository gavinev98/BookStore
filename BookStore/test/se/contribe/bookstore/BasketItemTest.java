package se.contribe.bookstore;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class BasketItemTest extends TestCase {

	public void testGetItemNumber() {

		
		
		String expectedString = "10  War and Peace  Leo Tolstoy  349.99  10";

		BookQty bQty = new BookQty("War and Peace", "Leo Tolstoy",new BigDecimal("349.99"), 10);
		BasketItem actualBI = new BasketItem(10, bQty);

		assertEquals(expectedString, actualBI.toString());
	}

}
