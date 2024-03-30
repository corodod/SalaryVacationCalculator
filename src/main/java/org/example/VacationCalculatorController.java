package org.example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@RestController
public class VacationCalculatorController {
    private final HolidayService holidayService;
    public VacationCalculatorController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
    @GetMapping("/calculate")
    public double calculateVacationPay(@RequestParam double avgSalary,
                                       @RequestParam int vacationDays,
                                       @RequestParam(required = false) String startDate,
                                       @RequestParam(required = false) String endDate) {
        double dailySalary = avgSalary / 365;
        double vacationPay;

        if (startDate != null && endDate != null) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            long workingDays = calculateWorkingDays(start, end);
            vacationPay = dailySalary * workingDays;
        } else {
            vacationPay = dailySalary * vacationDays;
        }
        return vacationPay;
    }

    private long calculateWorkingDays(LocalDate start, LocalDate end) {
        long totalDays = ChronoUnit.DAYS.between(start, end) + 1;
        long holidays = holidayService.countHolidays(start, end);
        long weekends = calculateWeekends(start, end);
        return totalDays - weekends - holidays;
    }

    private long calculateWeekends(LocalDate start, LocalDate end) {
        long weekends = 0;
        LocalDate current = start;
        while (!current.isAfter(end)) {
            if (current.getDayOfWeek() == java.time.DayOfWeek.SATURDAY ||
                    current.getDayOfWeek() == java.time.DayOfWeek.SUNDAY) {
                weekends++;
            }
            current = current.plusDays(1);
        }
        return weekends;
    }
}
