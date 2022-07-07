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

    private String getKey(String name, String surname){
        return name + " " + surname;
    }

    public Employee add(String name,
                        String surname,
                        int department,
                        double salary) {
        Employee employee = new Employee(name, surname, department, salary);
        if (employees.containsKey(getKey(name, surname))) {
            throw new EmployeeAlreadyAddedException(); 
        }
        if (employees.size() < limit) {
            employees.put(getKey(name, surname), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }

    public Employee remove(String name, String surname) {
        String key = getKey(name, surname);
        if (!employees.containsKey(key)){
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String name, String surname) {
        String key = getKey(name, surname);
        if (!employees.containsKey(getKey(name, surname))){
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public List<Employee> getAll(){
        return new ArrayList<>(employees.values());
    }

}
