/**
 * 
 */
package se.contribe.bookstore;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pezhman
 *
 */
public class Validator {

	public static boolean validateSearchString(String searchString) {
		// Regular expression:
		// ^$ recognizes empty string
		// (?:author|title) recognizes key words

		final Pattern VALID_SEARCH_STRING_REGEX = Pattern.compile(
				"^(?:author|title):[a-zA-Z0-9_]+( [a-zA-Z0-9_]+)*$",
				Pattern.CASE_INSENSITIVE);

		Matcher matcher = VALID_SEARCH_STRING_REGEX.matcher(searchString);
		return matcher.find();
	}

	/**
	 * This function validates the entry points to the menu in XWindow terminal
	 * 
	 * @param commandItem
	 * @return
	 */
	public static boolean isMenuCommandCorrect(String commandItem) {
		return commandItem.equals("1") || commandItem.equals("2")
				|| commandItem.equals("3") || commandItem.equals("4")
				|| commandItem.equals("5") || commandItem.equals("6")
				|| commandItem.equals("0");
	}

	public static boolean isValidBigDecimal(String price) {
		try {
			new BigDecimal(price);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isValidInt(String qty) {
		try {
			Integer.parseInt(qty);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

}
