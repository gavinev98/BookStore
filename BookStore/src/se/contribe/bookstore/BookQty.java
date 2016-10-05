/**
 * 
 */
package se.contribe.bookstore;

import java.math.BigDecimal;

/**
 * @author pezhman
 *
 */
public class BookQty extends Book {

	private int quantity;

	public BookQty() {
		super();
		this.quantity = 0;
	}

	/**
	 * A parameterized Constructor
	 * 
	 * @param title
	 * @param author
	 * @param price
	 */
	public BookQty(String title, String author, BigDecimal price,
			int quantity) {
		super(title, author, price);
		this.quantity = quantity;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#getTitle()
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return super.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		super.setTitle(title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#getAuthor()
	 */
	@Override
	public String getAuthor() {
		// TODO Auto-generated method stub
		return super.getAuthor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#setAuthor(java.lang.String)
	 */
	@Override
	public void setAuthor(String author) {
		// TODO Auto-generated method stub
		super.setAuthor(author);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#getPrice()
	 */
	@Override
	public BigDecimal getPrice() {
		// TODO Auto-generated method stub
		return super.getPrice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#setPrice(java.math.BigDecimal)
	 */
	@Override
	public void setPrice(BigDecimal price) {
		// TODO Auto-generated method stub
		super.setPrice(price);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.Book#toString()
	 */
	@Override
	public String toString() {
		return "  Title: " + super.getTitle() + "\n  Author: "
				+ super.getAuthor() + "\n  Price: " + super.getPrice()
				+ "\n  Qty: " + this.quantity;
	}

}
