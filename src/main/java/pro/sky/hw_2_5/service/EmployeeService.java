package pro.sky.hw_2_5.service;

import org.springframework.stereotype.Service;
import pro.sky.hw_2_5.exceptions.EmployeeAlreadyAddedException;
import pro.sky.hw_2_5.exceptions.EmployeeNotFoundException;
import pro.sky.hw_2_5.exceptions.EmployeeStorageIsFullException;
import pro.sky.hw_2_5.model.Employee;

import java.util.*;

@Service
public class EmployeeService {

    private static final int limit = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    private String getKey(Employee employee){
        return employee.getName() + " " + employee.getLastName();
    }

    public Employee add(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.containsKey(getKey(employee))) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < limit) {
            employees.put(getKey(employee), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surname) {
        Employee employee = new Employee(name, surname);
        String key = getKey(employee);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        employees.remove(key);
        return employee;
    }

    public Employee find(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.containsKey(getKey(employee))){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }

}
