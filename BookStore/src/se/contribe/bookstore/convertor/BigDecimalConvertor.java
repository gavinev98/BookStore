/**
 * 
 */
package se.contribe.bookstore.convertor;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

/**
 * @author pezhman
 *
 */
public class BigDecimalConvertor {
	
	/**
	 * This method converts the String type to the BigDecimal type
	 * @param price
	 * @return 
	 */
	public static BigDecimal toBigDecimal(String price) {
		

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		symbols.setDecimalSeparator('.');
		String pattern = "#,##0.0#";
		DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
		decimalFormat.setParseBigDecimal(true);

		// parse the string
		BigDecimal bigDecimal = null;
		try {
			bigDecimal = (BigDecimal) decimalFormat.parse(price);

		} catch (ParseException pe) {
			System.out.println("BookInventory> ERROR 02> " + pe.getMessage());
			pe.printStackTrace();
		}

		return bigDecimal;

	}

}
