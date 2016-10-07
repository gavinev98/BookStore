/**
 * 
 */
package se.contribe.bookstore.ui;

import java.util.Scanner;

import se.contribe.bookstore.Command;
import se.contribe.bookstore.Validator;

/**
 * @author pezhman
 *
 */
public class XWindowUI {

	/**
	 * creates a mechanism to check the validity of entered data for the menu as
	 * commands.
	 * 
	 * @return
	 */
	public static String createMenu() {
		boolean flagToLeaveLoop = false;
		String commandItem = "";

		try {
			@SuppressWarnings("resource")
			Scanner inScan = new Scanner(System.in);
			do {

				createMenuItemUi();
				commandItem = inScan.nextLine();

				if (Validator.isMenuCommandCorrect(commandItem))
					flagToLeaveLoop = false;
				else {
					flagToLeaveLoop = true;
					System.out.println("");
					System.out.println("!! !! ERROR: Wrong command number !!!");
					System.out.println(
							"!     Select one of the item in the list below.");

				}

			} while (flagToLeaveLoop);
		} catch (Exception e) {
			System.out.println("XWindowUI> ERROR 05> " + e.getMessage());
			e.printStackTrace();
		}

		return commandItem;

	}

	/**
	 * Create a user interface for (main) menu
	 */
	private static void createMenuItemUi() {
		System.out.println("");
		System.out.println("+--Main Manu------------------------------+");
		System.out.println("|  [ 1 ]  Show all books                  |");
		System.out.println("|  [ 2 ]  Show books (search by authors)  |");
		System.out.println("|  [ 3 ]  Show books (search by titles)   |");
		System.out.println("|  [ 4 ]  Add a book                      |");
		System.out.println("|  [ 5 ]  Remove a book                   |");
		System.out.println("|  [ 6 ]  Buy Books                       |");
		System.out.println("|                                         |");
		System.out.println("|  ( 0 )  Exit                            |");
		System.out.println("+-----------------------------------------+");
		System.out.print(" Select an item> ");
	}

	/**
	 * creates a mechanism to check the validity of entered data for the buy
	 * menu as commands
	 * 
	 * @return
	 */
	public static String createBuyMenu() {
		boolean flagToLeaveLoop = false;
		String commandItem = "";

		try {
			@SuppressWarnings("resource")
			Scanner inScan = new Scanner(System.in);
			do {

				createBuyMenuUi();
				commandItem = inScan.nextLine();

				if (Validator.isBuyMenuCommandCorrect(commandItem))
					flagToLeaveLoop = false;
				else {
					flagToLeaveLoop = true;
					System.out.println("");
					System.out.println("!! !! ERROR: Wrong command number !!!");
					System.out.println(
							"!     Select one of the item in the list below.");
				}

			} while (flagToLeaveLoop);
		} catch (Exception e) {
			System.out.println("XWindowUI> ERROR 05> " + e.getMessage());
			e.printStackTrace();
		}

		return commandItem;
	}

	/**
	 * Create a user interface for (buy) menu
	 */
	public static void createBuyMenuUi() {
		System.out.println("");
		System.out.println("+--Main Menu -> Buy Books-----------------+");
		System.out.println("|  [ 1 ]  Add a book to your basket       |");
		System.out.println("|  [ 2 ]  Remove a book from your basket  |");
		System.out.println("|  [ 3 ]  Show the basket items           |");
		System.out.println("|  [ 4 ]  Buy the current basket          |");
		System.out.println("|                                         |");
		System.out.println("|  ( 0 )  Exit (Drop the current order)   |");
		System.out.println("+-----------------------------------------+");
		System.out.print(" Select an item> ");
	}

	/**
	 * runs the (main) menu
	 */
	public static void runMenuManager() {
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
				runBuyMenuManager();
				break;

			default:
				break;
			}

		} while (flagToLeaveLoop);
	}

	/**
	 * 
	 */
	public static void runBuyMenuManager() {
		// Buy Books
		boolean flag = true;

		do {
			String commandBuyItem = XWindowUI.createBuyMenu();

			switch (commandBuyItem) {
			case "0":
				// Back to the main menu
				flag = false;
				break;

			case "1":
				// Add book to the basket
				Command.addBookToBasket();
				break;

			case "2":
				// Remove book from the basket
				Command.removeBookFromBasket();
				break;

			case "3":
				// Show items in the basket
				// true: report items in the basket with their id number.
				Command.showBasketItems(true);
				break;

			case "4":
				// Perform shopping
				Command.performShopping();
				break;

			default:
				break;
			}

		} while (flag);
	}
}
