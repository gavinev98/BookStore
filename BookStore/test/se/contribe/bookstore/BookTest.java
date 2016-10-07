package se.contribe.bookstore;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class BookTest extends TestCase {




	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();

	}

	public void testGetTitle(){

		Book book = new Book("Jane Eyre", "Charlotte Brontë", new BigDecimal("499.99"));

		assertEquals("Jane Eyre", book.getTitle());
	}

	public void testGetAuthor(){

		Book book = new Book("Jane Eyre", "Charlotte Brontë", new BigDecimal("499.99"));

		assertEquals("Charlotte Brontë", book.getAuthor());
	}

	public void testGetPrice(){


		Book book = new Book("Jane Eyre", "Charlotte Brontë", new BigDecimal("499.99"));

		assertEquals(new BigDecimal("499.99"), book.getPrice());

	}

}

