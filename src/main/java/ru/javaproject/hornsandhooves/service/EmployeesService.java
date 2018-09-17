package ru.javaproject.hornsandhooves.service;

import ru.javaproject.hornsandhooves.model.Employee;

import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

public interface EmployeesService {

    Employee save(Employee employee);

    void delete(int id) throws NotFoundException;

    Employee get(int id) throws NotFoundException;

    List<Employee> getAll();

    void update(Employee employee);

}
