package homeWork4;

import java.util.ArrayList;
import java.util.List;

public class EmployeeHandbook {

    private List<Employee> employees;

    public EmployeeHandbook(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> findEmployeeByWorkExperience(int experience) {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getWorkExperience() == experience) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<String> findPhoneNumbersByName(String name) {
        List<String> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getName().equals(name)) {
                result.add(employee.getPhoneNumber());
            }
        }
        return result;
    }

    public Employee findEmployeeByEmployeeId(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == employeeId) {
                return employee;
            }
        }
        return null; // Если сотрудник с указанным табельным номером не найден
    }
}
