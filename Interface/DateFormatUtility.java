import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

interface DateFormatUtility {
    static String formatDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}

public class DateFormatTest {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println(DateFormatUtility.formatDate(today, "dd/MM/yyyy"));
        System.out.println(DateFormatUtility.formatDate(today, "yyyy-MM-dd"));
    }
}
