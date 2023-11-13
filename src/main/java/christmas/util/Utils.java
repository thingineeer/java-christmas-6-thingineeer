package christmas.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utils {

    public static Map<String, Integer> makeStringToHashMap(String input) {
        Map<String, Integer> resultMap = Stream.of(input.split(","))
                .map(s -> s.split("-"))
                .collect(Collectors.toMap(
                        arr -> arr[0],
                        arr -> Integer.parseInt(arr[1]),
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
        return resultMap;
    }

    public static String makeFormattedNumberWithComma(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
}
