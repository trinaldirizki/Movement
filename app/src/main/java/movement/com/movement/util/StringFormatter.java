package movement.com.movement.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by isma-ilou on 17.06.2018.
 */

public class StringFormatter {

    public static String convertCurrency(int value){
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("Rp ###,###,###,###", symbols);
        String formattedText = decimalFormat.format(value);
        return formattedText;
    }

    public static String convertDistance(int value){
        float km = (float) value / 1000;
        String formattedText = km + " km";
        return formattedText;
    }
}
