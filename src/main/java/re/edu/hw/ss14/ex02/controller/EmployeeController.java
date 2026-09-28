package re.edu.hw.ss14.ex02.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import re.edu.hw.ss14.ex02.dto.response.EmployeeResponse;
import re.edu.hw.ss14.ex02.service.IEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final IEmployeeService employeeService;

    @GetMapping
    public List<EmployeeResponse> findAll() {
        return employeeService.findAll();
    }
}
