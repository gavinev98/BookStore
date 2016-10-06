/**
 * 
 */
package se.contribe.bookstore;

/**
 * @author pezhman
 *
 */
public class BasketItem {
	private int itemNumber;
	private BookQty bookItem;

	/**
	 * A parameterized Constructor
	 * 
	 * @param itemNumber
	 * @param bookItem
	 */
	public BasketItem(int itemNumber, BookQty bookItem) {
		this.itemNumber = itemNumber;
		this.bookItem = bookItem;
	}

	/**
	 * @return the itemNumber
	 */
	public int getItemNumber() {
		return this.itemNumber;
	}

	/**
	 * @param itemNumber
	 *            the itemNumber to set
	 */
	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	/**
	 * @return the bookItem
	 */
	public BookQty getBookItem() {
		return this.bookItem;
	}

	/**
	 * @param bookItem
	 *            the bookItem to set
	 */
	public void setBookItem(BookQty bookItem) {
		this.bookItem = bookItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return this.itemNumber + "  " + bookItem.getTitle() + "  "
				+ bookItem.getAuthor() + "  " + bookItem.getPrice() + "  "
				+ bookItem.getQuantity();
	}
}
