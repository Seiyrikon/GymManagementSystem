package Utilities;

public class DateParser {
    public int parseDateYYYYMMDDToInt(String date) {
        return Integer.parseInt(date.replace("-", ""));
    }
}
