/**
 * 
 */
package se.contribe.bookstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import se.contribe.bookstore.convertor.BigDecimalConvertor;

/**
 * @author pezhman
 *
 */
public class BookInventory implements BookList {

	private String filePath;
	private ArrayList<BasketItem> basketItemArrayList;

	/**
	 * Default constructor
	 */
	public BookInventory() {
		this.filePath = new File("bookstoredata.txt").getAbsolutePath();
	}

	public BookInventory(ArrayList<BasketItem> basketItemArrayList) {
		this();
		this.basketItemArrayList = basketItemArrayList;
	}

	/**
	 * reads the Book Data base file. Book database file is a text file located
	 * in the bin directory. Return a LinkedList that each elements is of type
	 * Book.
	 * 
	 * @return
	 */
	public ArrayList<Book> readBookDatabaseFile() {

		ArrayList<Book> tempBookTable = new ArrayList<>();
		BufferedReader fileReader = null;

		try {
			fileReader = new BufferedReader(new FileReader(this.filePath));

			String currentRecord = "";
			while ((currentRecord = fileReader.readLine()) != null) {

				// Separate each field by using the field
				// Splitter of the file which is semicolon
				String[] fields = currentRecord.split(";");

				tempBookTable.add(new Book(fields[0], fields[1],
						BigDecimalConvertor.toBigDecimal(fields[2])));
			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("BookInventory> ERROR 04> " + fnfe.getMessage());
			fnfe.getStackTrace();

		} catch (IOException ioe) {
			System.out.println("BookInventory> ERROR 01> " + ioe.getMessage());
			ioe.getStackTrace();

		} finally {
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (IOException ioe) {
					System.out.println(
							"BookInventory> ERROR 03> " + ioe.getMessage());

					ioe.printStackTrace();
				}
		}

		return tempBookTable;
	}

	/**
	 * reads all four fields of the database including: 1. book.bookTitle 2.
	 * book.bookAuthor 3. book.price 4. book.numberOfAvailableBook
	 * 
	 * @return
	 */
	public ArrayList<BookQty> readBookDatabaseFileWithQuantity() {

		ArrayList<BookQty> tempBookTable = new ArrayList<>();
		BufferedReader fileReader = null;

		try {
			fileReader = new BufferedReader(new FileReader(this.filePath));

			String currentRecord = "";
			while ((currentRecord = fileReader.readLine()) != null) {

				// Separate each field by using the field
				// Splitter of the file which is semicolon
				String[] fields = currentRecord.split(";");

				tempBookTable.add(new BookQty(fields[0], fields[1],
						BigDecimalConvertor.toBigDecimal(fields[2]),
						Integer.parseInt(fields[3])));
			}

		} catch (FileNotFoundException fnfe) {
			System.out.println("BookInventory> ERROR 04> " + fnfe.getMessage());
			fnfe.getStackTrace();

		} catch (IOException ioe) {
			System.out.println("BookInventory> ERROR 01> " + ioe.getMessage());
			ioe.getStackTrace();

		} finally {
			if (fileReader != null)
				try {
					fileReader.close();
				} catch (IOException ioe) {
					System.out.println(
							"BookInventory> ERROR 03> " + ioe.getMessage());

					ioe.printStackTrace();
				}
		}

		return tempBookTable;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {

		return this.filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {

		this.filePath = filePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.BookList#list(java.lang.String)
	 */
	@Override
	public Book[] list(String searchString) {

		// When the user wants to see all the books in the list
		ArrayList<Book> tempBookTable = new ArrayList<>();

		if (searchString.equals(""))
			tempBookTable = this.readBookDatabaseFile();

		else {
			// When the user wants to search the data base
			// bases on author or title.
			//
			// To search based on the author, the user put the
			// prefix author: (followed by a colon) in front of
			// the author's name.
			// for example: "author:John Dow"
			//
			// To search based on the title, the user put the
			// prefix title: (followed by a colon) in front of
			// the book title.
			// for example: "title:Life is beautiful"
			String[] items = searchString.split(":");

			switch (items[0]) {
			case "author":
				for (Book tempBook : this.readBookDatabaseFile()) {
					if (tempBook.getAuthor().equals(items[1])) {
						tempBookTable.add(tempBook);
					}
				}
				break;

			case "title":
				System.out.println("Result is based on TITLE:" + items[1]);
				for (Book tempBook : this.readBookDatabaseFile()) {
					if (tempBook.getTitle().equals(items[1])) {
						tempBookTable.add(tempBook);
					}
				}
				break;

			default:
				System.out.println("INVALID KEY");
				break;
			}

		}

		Book[] bookArray;

		bookArray = new Book[tempBookTable.size()];

		int i = 0;
		for (Book tempBook : tempBookTable) {
			bookArray[i] = tempBook;
			i++;
		}

		return bookArray;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.BookList#add(se.contribe.bookstore.Book, int)
	 */
	@Override
	public boolean add(Book book, int quantity) {

		File dbBook = new File(this.filePath);

		StringBuffer strBuffer = Book.createBookRecordForDataBase(book,
				quantity);

		FileWriter fileWriter;
		BufferedWriter bufferWriter = null;

		boolean successOperation = false;
		synchronized (dbBook) {

			try {
				fileWriter = new FileWriter(this.filePath, true);
				bufferWriter = new BufferedWriter(fileWriter);

				bufferWriter.write(strBuffer.toString());

				successOperation = true;
			} catch (IOException ioe) {
				System.out.println(
						"BookInventory> ERROR 07> " + ioe.getMessage());
				ioe.printStackTrace();
			} finally {
				if (bufferWriter != null)
					try {
						bufferWriter.close();
					} catch (IOException e) {
						System.out.println(
								"BookInventory> ERROR 08> " + e.getMessage());
						e.printStackTrace();
					}
			}
		}

		return successOperation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see se.contribe.bookstore.BookList#buy(se.contribe.bookstore.Book[])
	 */
	@Override
	public int[] buy(Book... books) {

		int[] status = { 0, 0, 0 };
		synchronized (this.filePath) {

			// list of books with quantity
			ArrayList<BookQty> bookQtyArrayList = this
					.readBookDatabaseFileWithQuantity();

			if (this.basketItemArrayList.size() != 0) {
				// elements from basket list
				for (BasketItem basketItem : this.basketItemArrayList) {

					boolean found = false;
					// elements from the book store database
					for (BookQty bookItemQty : bookQtyArrayList)

						// if both elements are exactly the same
						// i.e. same primary keys (PK's)
						// change the Quantity in the book store database
						// in the main
						if ((basketItem.getBookItem().getTitle()
								.equals(bookItemQty.getTitle()))
								&& (basketItem.getBookItem().getAuthor()
										.equals(bookItemQty.getAuthor()))
								&& (basketItem.getBookItem().getPrice()
										.compareTo(
												bookItemQty.getPrice()) == 0)) {
							found = true;
							if (bookItemQty.getQuantity() == 0)
								status[1]++;

							// update - ok
							int reminder = bookItemQty.getQuantity() - 1;
							bookItemQty.setQuantity(reminder);
						}
					if (found == false)
						status[2]++;

				}

				if (status[0] == 0 && status[1] == 0 && status[2] == 0) {

					FileWriter fileWriter;
					BufferedWriter bufferWriter = null;

					try {
						fileWriter = new FileWriter(this.filePath);
						bufferWriter = new BufferedWriter(fileWriter);

						bufferWriter
								.write(createTableContent(bookQtyArrayList));

					} catch (IOException ioe) {
						System.out.println(
								"BookInventory> ERROR 07> " + ioe.getMessage());
						ioe.printStackTrace();
					} finally {
						if (bufferWriter != null)
							try {
								bufferWriter.close();
							} catch (IOException e) {
								System.out.println("BookInventory> ERROR 08> "
										+ e.getMessage());
								e.printStackTrace();
							}
					}

				}

			} else {
				System.out.println("");
				System.out.println("!! ERROR: Basket is empty!!!");
				status[0]++;
			}

		}

		return status;
	}

	/**
	 * update number of available books after selling. In case, if the number of
	 * available books are zero, this function remove the book item from the
	 * database
	 * 
	 * @param book
	 * @param removedQuantity
	 * @return
	 */
	public boolean remove(Book book, int removedQuantity) {

		ArrayList<BookQty> tempBookTable = new ArrayList<>();
		BookQty oldBook = new BookQty();

		for (BookQty bookItem : this.readBookDatabaseFileWithQuantity())
			tempBookTable.add(bookItem);

		for (Iterator<BookQty> ite = tempBookTable.iterator(); ite.hasNext();) {
			BookQty bookItem = ite.next();
			if (bookItem.getTitle().equals(book.getTitle())
					&& bookItem.getAuthor().equals(book.getAuthor())
					&& (bookItem.getPrice().compareTo(book.getPrice()) == 0)) {

				oldBook.setTitle(bookItem.getTitle());
				oldBook.setAuthor(bookItem.getAuthor());
				oldBook.setPrice(bookItem.getPrice());
				oldBook.setQuantity(bookItem.getQuantity());
				ite.remove();
			}
		}

		int reminder = oldBook.getQuantity() - removedQuantity;

		boolean successOperation = false;
		if (reminder == 0) {

			String table = createTableContent(tempBookTable);
			successOperation = rewriteTable(table);
		} else if (reminder > 0) {
			oldBook.setQuantity(reminder);
			tempBookTable.add(oldBook);
			String table = createTableContent(tempBookTable);
			successOperation = rewriteTable(table);
			// System.out.println(table);
		} else
			System.out.println(
					"Number of demanded books are more than the number of available books");

		return successOperation;
	}

	/**
	 * It writes the whole database in case of removing a book item or a
	 * updating the number of available books. of available books.
	 * 
	 * @param successOperation
	 * @param table
	 * @return
	 */
	public boolean rewriteTable(String table) {

		boolean successOperation = false;
		File dbBook = new File(this.filePath);

		FileWriter fileWriter;
		BufferedWriter bufferWriter = null;

		synchronized (dbBook) {

			try {
				fileWriter = new FileWriter(this.filePath);
				bufferWriter = new BufferedWriter(fileWriter);

				bufferWriter.write(table);

				successOperation = true;
			} catch (IOException ioe) {
				System.out.println(
						"BookInventory> ERROR 07> " + ioe.getMessage());
				ioe.printStackTrace();
			} finally {
				if (bufferWriter != null)
					try {
						bufferWriter.close();
					} catch (IOException e) {
						System.out.println(
								"BookInventory> ERROR 08> " + e.getMessage());
						e.printStackTrace();
					}
			}

		}
		return successOperation;
	}

	/**
	 * Create a text contains all the records of the database.
	 * 
	 * @param BookArrayList
	 * @return
	 */
	public String createTableContent(ArrayList<BookQty> BookArrayList) {
		StringBuffer strTable = new StringBuffer();
		for (BookQty bookItem : BookArrayList) {
			strTable.append(BookQty.createBookRecordForDataBase(bookItem,
					bookItem.getQuantity()));
		}
		return strTable.toString();

	}
}
