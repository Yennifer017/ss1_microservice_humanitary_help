package ss1.ong.humanitary.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MoneyUtil {

    private MoneyUtil() { }

    public static Double round(Double number){
        return BigDecimal
                .valueOf(number)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

}
