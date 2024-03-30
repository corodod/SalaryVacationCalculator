package org.example;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class VacationEntity {
    private Integer vacationDays;
    private Double salary;
    private LocalDate startVacation;
    private LocalDate endVacation;
    //геттеры, сеттеры и конструкторы
}
