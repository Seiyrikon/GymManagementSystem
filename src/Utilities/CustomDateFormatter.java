package Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomDateFormatter {
    public String dateFormatterMMMMdyyyy(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        return date.format(formatter);
    }
}
