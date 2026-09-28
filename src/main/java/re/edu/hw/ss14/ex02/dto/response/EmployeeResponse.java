package re.edu.hw.ss14.ex02.dto.response;

import java.math.BigDecimal;

public record EmployeeResponse(Long id, String fullName, BigDecimal salary) {
}
