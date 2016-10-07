/**
 * 
 */
package se.contribe.bookstore;

import java.math.BigDecimal;

/**
 * @author pezhman
 *
 */
public class Book {

	private String title;
	private String author;
	private BigDecimal price;

	/**
	 * Default Constructor that creates a Book object.
	 */
	public Book() {
		this.title = "unknown";
		this.author = "unknown";
		this.price = new BigDecimal("0.00");
	}

	/**
	 * A parameterized Constructor
	 * 
	 * @param title
	 * @param author
	 * @param price
	 */
	public Book(String title, String author, BigDecimal price) {
		this.title = title;
		this.author = author;
		this.price = price;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "  Title: " + this.title + "\n  Author: " + this.author
				+ "\n  Price: " + this.price;
	}

	/**
	 * creates a record. This record can be stored in the text file. the record
	 * structure is as follows: bookname;authorsname;bookprice;numberofbook
	 * semicolon is the spliter between fields
	 * 
	 * @param book
	 * @param quantity
	 * @return
	 */
	public static StringBuffer createBookRecordForDataBase(Book book,
			int quantity) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(book.getTitle() + ";");
		strBuffer.append(book.getAuthor() + ";");
		strBuffer.append(book.getPrice() + ";");
		strBuffer.append(quantity + "\n");
		return strBuffer;
	}

}
