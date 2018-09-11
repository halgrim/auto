package de.auto;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyHelper {

    public static int reformatValueToCalulatorFormat(String price)
    {
    	NumberFormat currencyInstance = NumberFormat.getInstance(Locale.GERMANY);
    	int parsedPrice = -99999;
		try {
			parsedPrice = currencyInstance.parse(price).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
    
        return parsedPrice;

    }
}