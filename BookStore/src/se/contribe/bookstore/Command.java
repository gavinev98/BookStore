/**
 * 
 */
package se.contribe.bookstore;

import java.math.BigDecimal;
import java.util.Scanner;

import se.contribe.bookstore.convertor.BigDecimalConvertor;

/**
 * @author pezhman
 *
 */
public class Command {

	public static void showAllBookRecords() {
		BookInventory bookInventoryObject = new BookInventory();
		Book[] bookTable = bookInventoryObject.list("");

		createReportTitle(".. .. .. .. REPORT: Show all books .. .. .. ..");
		if (bookTable.length == 0) {
			System.out.println("");
			System.out.println("No Record was found in the database.");
		} else {
			if (bookTable != null) {
				int i = 1;
				for (Book bookRecord : bookTable) {
					System.out.println("> Book item no. " + i);
					System.out.println(bookRecord.toString());
					System.out.println("");
					i++;

				}
				showTotalNumRecords(i);
			}
		}

	}

	/**
	 * @param numberResult
	 */
	private static void showTotalNumRecords(int numberResult) {
		System.out.println(
				"* Total number of results: " + numberResult + " record(s)");
	}

	public static void showBookRecordSearchedAuthor() {
		String authorName = "";

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);

		BookInventory bookInventoryObject = new BookInventory();

		try {
			createEnterMessage("Enter author name> ");
			authorName = inScan.nextLine();

			Book[] bookTable = bookInventoryObject.list("author:" + authorName);
			createReportTitle("..  REPORT: Show books  (search by authors) ..");
			if (bookTable.length == 0) {
				System.out.println("No Record was found in the "
						+ "database. SEARCH KEY (author): " + authorName);
			} else {
				if (bookTable != null) {
					int i = 1;
					for (Book bookRecord : bookTable) {
						System.out.println("> Book item no. " + i);
						System.out.println(bookRecord.toString());
						System.out.println("");
						i++;
					}
					showTotalNumRecords(i);
				}
			}

		} catch (Exception e) {
			System.out.println("Command> ERROR 06> " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void showBookRecordSearchedTitle() {
		String titleName = "";

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);

		BookInventory bookInventoryObject = new BookInventory();

		try {
			createEnterMessage("Enter book title> ");
			titleName = inScan.nextLine();

			Book[] bookTable = bookInventoryObject.list("title:" + titleName);
			createReportTitle("..  REPORT: Show books  (search by titles)  ..");
			if (bookTable.length == 0) {
				System.out.println("No Record was found in the "
						+ "database. SEARCH KEY (titles): " + titleName);
			} else {
				if (bookTable != null) {
					int i = 1;
					for (Book bookRecord : bookTable) {
						System.out.println("> Book item no. " + i);
						System.out.println(bookRecord.toString());
						System.out.println("");
						i++;
					}
					showTotalNumRecords(i);
				}
			}

		} catch (Exception e) {
			System.out.println("Command> ERROR 06> " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * @param enterMessage
	 */
	private static void createEnterMessage(String enterMessage) {
		System.out.println("");
		System.out.println("..                                          ..");
		System.out.println("..                                          ..");
		System.out.print(". " + enterMessage);
	}

	/**
	 * @param reportTitle
	 */
	private static void createReportTitle(String reportTitle) {
		System.out.println("");
		System.out.println(".                                            .");
		System.out.println(".                                            .");
		System.out.println(reportTitle);
		System.out.println("==============================================");
		System.out.println("");
	}

	public static void addBook() {
		String title = "";
		String author = "";
		String price = "";
		BigDecimal bdPrice = new BigDecimal("0");
		String qty = "";
		int iQty = 0;

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);
		createEnterMessage("Enter the book title> ");
		title = inScan.nextLine();

		createEnterMessage("Enter author of the book> ");
		author = inScan.nextLine();

		boolean flagLeaveLoop = true;
		do {
			createEnterMessage("Enter price of the book> ");
			price = inScan.nextLine();

			if (Validator.isValidBigDecimal(price)) {
				bdPrice = BigDecimalConvertor.toBigDecimal(price);
				flagLeaveLoop = false;
			} else {
				System.out.println("");
				System.out.println("!! ERROR: Wrong price !!!");
				System.out.println("!     Price contains only digits "
						+ "and seperator such as ´.´.");
				System.out.println("!     For example: 1590.83");
			}
		} while (flagLeaveLoop);

		flagLeaveLoop = true;
		do {
			createEnterMessage("Enter the number of the book> ");
			qty = inScan.nextLine();

			if (Validator.isValidInt(qty)) {
				iQty = Integer.parseInt(qty);
				flagLeaveLoop = false;
			} else {
				System.out.println("");
				System.out.println("!! ERROR: Wrong quantity !!!");
				System.out.println("!     Quantity contains only integers.");
				System.out.println("!     For example: 2762");
			}
		} while (flagLeaveLoop);

		Book bookItem = new Book(title, author, bdPrice);
		BookInventory bookInventoryObject = new BookInventory();
		if (bookInventoryObject.add(bookItem, iQty)) {
			System.out.println("");
			System.out.println(".. NOTE: 1 book item is added to the "
					+ "database successfully.");
			System.out.println("");
		}

	}

	public static void removeBook() {

		String title = "";
		String author = "";
		String price = "";
		BigDecimal bdPrice = new BigDecimal("0");
		String removedQty = "";
		int iRemovedQty = 0;

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);
		createEnterMessage("Enter the book title> ");
		title = inScan.nextLine();

		createEnterMessage("Enter author of the book> ");
		author = inScan.nextLine();

		boolean flagLeaveLoop = true;
		do {
			createEnterMessage("Enter price of the book> ");
			price = inScan.nextLine();

			if (Validator.isValidBigDecimal(price)) {
				bdPrice = BigDecimalConvertor.toBigDecimal(price);
				flagLeaveLoop = false;
			} else {
				System.out.println("");
				System.out.println("!! ERROR: Wrong price !!!");
				System.out.println("!     Price contains only digits "
						+ "and seperator such as ´.´.");
				System.out.println("!     For example: 1590.83");
			}
		} while (flagLeaveLoop);

		flagLeaveLoop = true;
		do {
			createEnterMessage("Enter the number of the book> ");
			removedQty = inScan.nextLine();

			if (Validator.isValidInt(removedQty)) {
				iRemovedQty = Integer.parseInt(removedQty);
				flagLeaveLoop = false;
			} else {
				System.out.println("");
				System.out.println("!! ERROR: Wrong removed quantity item !!!");
				System.out.println("!     Quantity contains only integers.");
				System.out.println("!     For example: 2762");
			}
		} while (flagLeaveLoop);

		Book bookItem = new Book(title, author, bdPrice);
		BookInventory bookInventoryObject = new BookInventory();
		if (bookInventoryObject.remove(bookItem, iRemovedQty)) {
			System.out.println("");
			System.out.println(
					".. NOTE: " + iRemovedQty + " book item(s) are removed "
							+ "from the stock successfully.");
			System.out.println("");
		}

	}

}
