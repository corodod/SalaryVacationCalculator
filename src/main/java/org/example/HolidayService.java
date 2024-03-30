package org.example;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class HolidayService {
    //предположим, что у нас есть список праздников в виде множества дат
    private Set<LocalDate> holidays = new HashSet<>();
    //конструктор для добавления праздников в список
    public HolidayService() {
        //пример добавления праздников в список (даты могут быть любыми)
        holidays.add(LocalDate.of(2024, 1, 1)); //новый год
        holidays.add(LocalDate.of(2024, 3, 8)); //8 марта
    }

    public long countHolidays(LocalDate start, LocalDate end) {
        long count = 0;
        LocalDate current = start;
        while (!current.isAfter(end)) {
            if (holidays.contains(current)) {
                count++;
            }
            current = current.plusDays(1);
        }
        return count;
    }
}
