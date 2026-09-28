package re.edu.hw.ss14.ex02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.hw.ss14.ex02.entity.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {
}
