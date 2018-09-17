package ru.javaproject.hornsandhooves.repository;

import ru.javaproject.hornsandhooves.model.Employee;

import java.util.List;

public interface EmployeesRepository {

    Employee save(Employee employee);

    boolean delete(int id);

    Employee get(int id);

    List<Employee> getAll();
}
