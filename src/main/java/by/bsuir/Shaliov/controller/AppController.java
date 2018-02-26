package by.bsuir.Shaliov.controller;

import by.bsuir.Shaliov.model.Employee;
import by.bsuir.Shaliov.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

/**
 * @author Shaliou_AG
 */
@Controller
@RequestMapping("/")
public class AppController {
    private static final String NON_UNIQ_SSN = "non.unique.ssn";
    private static final String REGISTRATION = "registration";
    private static final String SUCCESS = "success";
    private static final String EMPLOYEE = "employee";
    private static final String EMPLOYEES = "employees";
    private static final String ALL_EMPLOYEES = "allemployees";
    private static final String EDIT = "edit";
    private static final String SSN = "ssn";
    private static final String REDIRECT = "redirect:";


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listEmployees(ModelMap model) {
        List<Employee> employeeList = employeeService.findAllEmployees();
        model.addAttribute(EMPLOYEES, employeeList);
        return ALL_EMPLOYEES;
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        Employee employee = new Employee();
        model.addAttribute(EMPLOYEE, employee);
        model.addAttribute(EDIT, false);
        return REGISTRATION;
    }

    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveEmployee(@Valid Employee employee, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return REGISTRATION;
        }
        if (checkSsn(employee, result)) {
            return REGISTRATION;
        }

        employeeService.updateEmployee(employee);
        modelMap.addAttribute(SUCCESS, "Employee " + employee.getName() + " registered successfully"); // добавить тут локаль
        return SUCCESS;
    }

    @RequestMapping(value = {"edit-{ssn}-employee"}, method = RequestMethod.GET)
    public String editEmployee(@PathVariable String ssn, ModelMap modelMap) {
        Employee employee = employeeService.findEmployeeBySsn(ssn);
        modelMap.addAttribute(EMPLOYEE, employee);
        modelMap.addAttribute(EDIT, true);
        return REGISTRATION;
    }

    @RequestMapping(value = {"edit-{ssn}-employee"}, method = RequestMethod.POST)
    public String updateEmployee(@Valid Employee employee, BindingResult result, ModelMap modelMap, @PathVariable String ssn) {
        if (result.hasErrors()) {
            return REGISTRATION;
        }
        if (checkSsn(employee, result)) {
            return REGISTRATION;
        }

        employeeService.updateEmployee(employee);
        modelMap.addAttribute(SUCCESS, "Employee " + employee.getName() + " updated successfully"); // добавить локаль
        return SUCCESS;
    }

    @RequestMapping(value = {"/delete-{ssn}-employee"}, method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable String ssn) {
        employeeService.deleteEmployeeBySsn(ssn);
        return REDIRECT + "/list";
    }

    private boolean checkSsn(@Valid Employee employee, BindingResult result) {
        if (!employeeService.isEmployeeSsnUnique(employee.getId(), employee.getSsn())) {
            FieldError ssnError = new FieldError(EMPLOYEE, SSN, messageSource.getMessage(NON_UNIQ_SSN, new String[]{employee.getSsn()}, Locale.getDefault()));
            result.addError(ssnError);
            return true;
        }
        return false;
    }
}
