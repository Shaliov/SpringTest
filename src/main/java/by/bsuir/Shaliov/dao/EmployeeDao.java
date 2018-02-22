package by.bsuir.Shaliov.dao;

import by.bsuir.Shaliov.model.Employee;

import java.util.List;

/**
 * @author Shaliou_AG
 */
public interface EmployeeDao {
    Employee findById(int id);
    void saveEmployee(Employee employee);
    void deleteEmployeeBySsn(String ssn);
    List<Employee> findAllEmployees();
    Employee findEmployeeBySsn(String ssn);
}
