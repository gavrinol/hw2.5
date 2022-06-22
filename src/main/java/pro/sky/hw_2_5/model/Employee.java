package pro.sky.hw_2_5.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Employee {

    private final String name;
    private final String lastName;


    public Employee(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }
    public String getName() {
        return name;
    }

    public String getLastName(){
        return lastName;
    }
    public String toString(){
        return name + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }
}