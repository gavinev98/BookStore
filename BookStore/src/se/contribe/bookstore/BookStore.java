/**
 * 
 */
package se.contribe.bookstore;

import se.contribe.bookstore.ui.XWindowUI;

/**
 * @author pezhman
 *
 */
public class BookStore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// BookInventory b = new BookInventory();
		/*
		 * Book[] b1 = b.list("");
		 * 
		 * if(b1 != null) for(Book bArr : b1) System.out.println("\n\n>>>"+
		 * bArr.toString());
		 * 
		 * System.out.println(">>>>>>>> <<<<<<<");
		 * 
		 * Book[] b2 = b.list("author:Cunning Bastard");
		 * 
		 * if(b2 != null) for(Book bArr : b2) System.out.println("\n\n>>>"+
		 * bArr.toString()); else System.out.println("NO RESULT");
		 */

		boolean flagToLeaveLoop = true;
		do {
			String commandItem = XWindowUI.createMenu();

			switch (commandItem) {
			case "0":
				System.out.println("Thank you for using this application.");
				flagToLeaveLoop = false;
				break;

			case "1":
				// Show all books
				Command.showAllBookRecords();
				break;

			case "2":
				// Show books (search by authors)
				Command.showBookRecordSearchedAuthor();
				break;

			case "3":
				// Show books (search by titles)
				Command.showBookRecordSearchedTitle();
				break;

			case "4":
				// Add a book
				Command.addBook();
				break;

			case "5":
				// Remove a book
				Command.removeBook();
				break;

			case "6":
				// Buy Books
				System.out.println("6. Buy Books");
				break;

			default:
				break;
			}

		} while (flagToLeaveLoop);

		System.exit(0);

	}

}
