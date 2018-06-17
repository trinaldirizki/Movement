package movement.com.movement.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * Created by isma-ilou on 17.06.2018.
 */

public class StringFormatter {

    private static DecimalFormat getFormatter() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat format = new DecimalFormat("Rp ###,###,###,###", symbols);
        return format;
    }

    public static String convertCurrency(int value){
        return getFormatter().format(value);
    }

    public static String convertDistance(int value) {
        float km = (float) value / 1000;
        return getFormatter().format(km);
    }
}
