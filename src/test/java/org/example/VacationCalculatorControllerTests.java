package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VacationCalculatorController.class)
public class VacationCalculatorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidayService holidayService;

    @Test
    public void testCalculateVacationPayWithoutDates() throws Exception {
        double avgSalary = 50000;
        int vacationDays = 14;

        mockMvc.perform(get("/calculate")
                        .param("avgSalary", String.valueOf(avgSalary))
                        .param("vacationDays", String.valueOf(vacationDays)))
                .andExpect(status().isOk());
        //добавить проверку ожидаемого результата, если нужно
    }

    @Test
    public void testCalculateVacationPayWithDates() throws Exception {
        double avgSalary = 50000;
        String startDate = "2024-03-01";
        String endDate = "2024-03-14";
        long workingDays = 10; //предположим, что между этими датами 10 рабочих дней

        when(holidayService.countHolidays(LocalDate.parse(startDate), LocalDate.parse(endDate)))
                .thenReturn(2L); //предположим, что в этот период 2 праздника

        mockMvc.perform(get("/calculate")
                        .param("avgSalary", String.valueOf(avgSalary))
                        .param("startDate", startDate)
                        .param("endDate", endDate))
                .andExpect(status().isOk());
    }
}

