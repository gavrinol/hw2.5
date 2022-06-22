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
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < limit) {
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)){
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String name, String surname) {
        Employee employee = new Employee(name, surname);
        if (!employees.contains(employee)){
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees);
    }

}
