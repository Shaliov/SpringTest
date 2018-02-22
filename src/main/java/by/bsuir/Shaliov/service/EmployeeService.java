package by.bsuir.Shaliov.service;

import by.bsuir.Shaliov.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shaliou_AG
 */
public interface EmployeeService {
    Employee findById(int id);
    void saveEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployeeBySsn(String ssn);
    List<Employee> findAllEmployees();
    Employee findEmployeeBySsn(String ssn);
    boolean isEmployeeSsnUnique(Integer id, String ssn);
}
