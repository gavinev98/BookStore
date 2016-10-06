/**
 * 
 */
package se.contribe.bookstore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import se.contribe.bookstore.convertor.BigDecimalConvertor;

/**
 * @author pezhman
 *
 */
public class Command {

	/**
	 * shows all available books in the database
	 */
	private static ArrayList<BasketItem> basketItemArrayList = new ArrayList<>();

	public static void showAllBookRecords() {

		BookInventory bookInventoryObject = new BookInventory();
		Book[] bookTable = bookInventoryObject.list("");

		createReportTitle(".. .. .. .. REPORT: Show All Books .. .. .. ..");
		if (bookTable.length == 0) {
			System.out.println("");
			System.out.println("No Record was found in the database.");
		} else {
			showRecordList(bookTable);
		}
	}

	/**
	 * prints all books item in an array of Books returning from the list method
	 * in the BookInventory class.
	 * 
	 * @param bookTable
	 */
	public static void showRecordList(Book[] bookTable) {
		if (bookTable != null) {
			int i = 1; // book counter
			for (Book bookRecord : bookTable) {
				System.out.println("> Book item no. " + i);
				System.out.println(bookRecord.toString());
				System.out.println("");
				i++;
			}

			// decrease by one, because i is initialized by 1.
			showTotalNumRecords(--i);
		}
	}

	/**
	 * Generate a message to show the total number of books
	 * 
	 * @param numberResult
	 */
	private static void showTotalNumRecords(int numberResult) {
		System.out.println(
				"* Total number of results: " + numberResult + " record(s)");
	}

	/**
	 * Shows the results of the book searched by author's name
	 */
	public static void showBookRecordSearchedAuthor() {

		System.out.println("");
		System.out.println(">> Search by Author");
		System.out.println("===================");
		String authorName = "";

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);

		BookInventory bookInventoryObject = new BookInventory();

		try {
			createEnterMessage("Enter ´author´ name> ");
			authorName = inScan.nextLine();

			Book[] bookTable = bookInventoryObject.list("author:" + authorName);
			createReportTitle("..  REPORT: Show books  (search by authors) ..");
			if (bookTable.length == 0) {
				System.out.println("No Record was found in the "
						+ "database. SEARCH KEY (author): " + authorName);
			} else {
				showRecordList(bookTable);
			}

		} catch (Exception e) {
			System.out.println("Command> ERROR 06> " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Shows the results of the book searched by title
	 */
	public static void showBookRecordSearchedTitle() {

		System.out.println("");
		System.out.println(">> Search by Title");
		System.out.println("==================");
		String titleName = "";

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);

		BookInventory bookInventoryObject = new BookInventory();

		try {
			createEnterMessage("Enter book ´title´> ");
			titleName = inScan.nextLine();

			Book[] bookTable = bookInventoryObject.list("title:" + titleName);
			createReportTitle("..  REPORT: Show books  (search by titles)  ..");
			if (bookTable.length == 0) {
				System.out.println("No Record was found in the "
						+ "database. SEARCH KEY (titles): " + titleName);
			} else {
				showRecordList(bookTable);
			}

		} catch (Exception e) {
			System.out.println("Command> ERROR 06> " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * creates a message for users when they should enter some data from
	 * console.
	 * 
	 * @param enterMessage
	 */
	private static void createEnterMessage(String enterMessage) {
		System.out.println("..                                          ..");
		System.out.print(". " + enterMessage);
	}

	/**
	 * Create a headline or title for reports.
	 * 
	 * @param reportTitle
	 */
	private static void createReportTitle(String reportTitle) {

		System.out.println("");
		System.out.println("..                                          ..");
		System.out.println("..                                          ..");
		System.out.println(reportTitle);
		System.out.println("==============================================");
		System.out.println("");
	}

	/**
	 * adds a book to the database. It provides necessary data for the add
	 * function (in class BookInventory) to add the data to the database.
	 */
	public static void addBook() {

		System.out.println("");
		System.out.println(">> Add a Book to Database");
		System.out.println("=========================");

		String title = "";
		String author = "";
		String price = "";
		BigDecimal bdPrice = new BigDecimal("0");
		String qty = "";
		int iQty = 0;

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);
		createEnterMessage("Enter the book ´title´> ");
		title = inScan.nextLine();

		createEnterMessage("Enter ´author´ of the' book> ");
		author = inScan.nextLine();

		boolean flagLeaveLoop = true;
		do {
			createEnterMessage("Enter ´price´ of the book> ");
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
			createEnterMessage("Enter the ´number´ of the book> ");
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

	/**
	 * removes or updates a book in the database. It provides necessary data for
	 * the remove function (in class BookInventory) to remove or update the data
	 * in the database.
	 */
	public static void removeBook() {

		System.out.println("");
		System.out.println(">> Release Books from Database");
		System.out.println("==============================");

		String title = "";
		String author = "";
		String price = "";
		BigDecimal bdPrice = new BigDecimal("0");
		String removedQty = "";
		int iRemovedQty = 0;

		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);
		createEnterMessage("Enter the book ´title´> ");
		title = inScan.nextLine();

		createEnterMessage("Enter ´author´ of the book> ");
		author = inScan.nextLine();

		boolean flagLeaveLoop = true;
		do {
			createEnterMessage("Enter ´price´ of the book> ");
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
			createEnterMessage("Enter the ´number´ of the book> ");
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

	/**
	 * provides necessary data to add books to the basket
	 */
	public static void addBookToBasket() {

		System.out.println("");
		System.out.println(">> >> Add a Book to Basket");
		System.out.println("==========================");

		// Show the available book item to select from
		ArrayList<BasketItem> bookItemList = new ArrayList<>();
		BookInventory bookInventoryObject = new BookInventory();
		int i = 1;
		for (BookQty book : bookInventoryObject
				.readBookDatabaseFileWithQuantity()) {
			bookItemList.add(new BasketItem(i, book));
			i++;
		}

		// Display available books for shopping
		createShoppingCatalogue(bookItemList);

		int selectedItem;
		selectedItem = validateSelectedItemNumber(i);

		// -1 means cancel the operation
		if (selectedItem != -1) {
			System.out.println(".. NOTE: The item ´"
					+ bookItemList.get(selectedItem).getBookItem().getTitle()
					+ "´ was added to your basket.");

			// Add an item to the basket
			basketItemArrayList.add(bookItemList.get(selectedItem));
		}

		BigDecimal totalPrice = new BigDecimal("0.00");
		System.out.println("..                       ..");
		System.out.println(".. Your basket includes: ..");
		System.out.println("===========================");
		for (BasketItem b : basketItemArrayList) {
			System.out.println(b.getBookItem().getTitle());
			totalPrice = b.getBookItem().getPrice().add(totalPrice);
		}
		System.out.println("---------------------------");
		System.out.println(
				"* Total of ordered books: " + basketItemArrayList.size());
		System.out.println("* Total price: " + totalPrice.toString() + " SEK.");
		System.out.println("===========================");
	}

	/**
	 * @param i
	 */
	public static int validateSelectedItemNumber(int i) {
		@SuppressWarnings("resource")
		Scanner inScan = new Scanner(System.in);

		String itemNo = "";

		boolean flag = true;
		do {
			itemNo = inScan.nextLine();
			if (Validator.isValidInt(itemNo)) {
				flag = false;
				if (Integer.parseInt(itemNo) >= i
						|| Integer.parseInt(itemNo) < 0) {
					flag = true;
					System.out.println("!! ERROR: No such an item number !!!");
					createEnterMessage(
							"Enter the book ´number´ to order or ´0´ to Cancel> ");
				}
			} else {
				System.out.println("");
				System.out.println("!! ERROR: Wrong item number !!!");
				System.out
						.println("!         Item numbers contain only digits ");
				System.out.println("!         For example: 13");
				createEnterMessage(
						"Enter the book ´number´ to order or ´0´ to Cancel> ");
			}
		} while (flag);

		int bookCode = Integer.parseInt(itemNo);
		return bookCode - 1;
	}

	/**
	 * @param bookItemList
	 */
	public static void createShoppingCatalogue(
			ArrayList<BasketItem> bookItemList) {
		String header = String.format("%1$5s  %2$-50s  %3$-30s  %4$10s  %5$5s",
				"No.", "Book Title", "Author", "Price", "Qty");

		char ch = '-';
		char[] characters = new char[110];
		Arrays.fill(characters, ch);
		characters[6] = '+';
		characters[58] = '+';
		characters[90] = '+';
		characters[102] = '+';

		System.out.println("");
		System.out.println(header);
		System.out.println(characters);

		for (BasketItem bookItem : bookItemList) {

			String tempString = String.format(
					"%1$5s |%2$-50s |%3$-30s |%4$10s |%5$5s",
					bookItem.getItemNumber(), bookItem.getBookItem().getTitle(),
					bookItem.getBookItem().getAuthor(),
					bookItem.getBookItem().getPrice(),
					bookItem.getBookItem().getQuantity());

			System.out.println(tempString);
		}
		createEnterMessage(
				"Enter the book ´number´ to order or ´0´ to Cancel> ");
		System.out.print("");
	}

	/**
	 * provides necessary data to remove books from the basket
	 */
	public static void removeBookFromBasket() {

		System.out.println("");
		System.out.println(">> >> Remove a Book from Basket");
		System.out.println("===============================");
	}

	/**
	 * order the shopping
	 */
	public static void performShopping() {

		System.out.println("");
		System.out.println(">> >> Perform shopping");
		System.out.println("======================");
	}
}