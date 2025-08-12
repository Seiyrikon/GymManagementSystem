package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateParser {
    public int parseDateYYYYMMDDToInt(String date) {
        return Integer.parseInt(date.replace("-", ""));
    }

    public String removeDash(String date) {
        return date.replace("-", "");
    }

    public LocalDate convertToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.parse(date, formatter);
    }
}
