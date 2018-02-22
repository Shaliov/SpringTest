package by.bsuir.Shaliov.service;

import by.bsuir.Shaliov.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shaliou_AG
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public void saveEmployee(Employee employee) {

    }

    @Override
    public void updateEmployee(Employee employee) {

    }

    @Override
    public void deleteEmployeeBySsn(String ssn) {

    }

    @Override
    public List<Employee> findAllEmployees() {
        return null;
    }

    @Override
    public Employee findEmployeeBySsn(String ssn) {
        return null;
    }

    @Override
    public boolean isEmployeeSsnUnique(Integer id, String ssn) {
        return false;
    }
}
