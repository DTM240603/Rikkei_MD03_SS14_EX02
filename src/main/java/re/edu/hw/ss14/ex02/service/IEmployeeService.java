package re.edu.hw.ss14.ex02.service;

import re.edu.hw.ss14.ex02.dto.response.EmployeeResponse;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeResponse> findAll();
}
