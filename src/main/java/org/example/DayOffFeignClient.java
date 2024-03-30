package org.example;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;

public interface DayOffFeignClient {
    @GetMapping("/{date}")
    String getDayOff(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate date);
}

