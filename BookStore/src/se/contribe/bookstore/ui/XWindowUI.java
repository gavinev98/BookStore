/**
 * 
 */
package se.contribe.bookstore.ui;

import java.util.Scanner;

import se.contribe.bookstore.Validator;

/**
 * @author pezhman
 *
 */
public class XWindowUI {

	public static String createMenu() {
		boolean flagToLeaveLoop = false;
		String commandItem = "";

		try {
			@SuppressWarnings("resource")
			Scanner inScan = new Scanner(System.in);
			do {

				menuItem();
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
	 * 
	 */
	private static void menuItem() {
		System.out.println("+-----------------------------------+");
		System.out.println("| 1. Show all books                 |");
		System.out.println("| 2. Show books (search by authors) |");
		System.out.println("| 3. Show books (search by titles)  |");
		System.out.println("| 4. Add a book                     |");
		System.out.println("| 5. Remove a book                  |");
		System.out.println("| 6. Buy Books                      |");
		System.out.println("| 0. Exit                           |");
		System.out.println("+-----------------------------------+");
		System.out.print(" Select an item> ");
	}

}
