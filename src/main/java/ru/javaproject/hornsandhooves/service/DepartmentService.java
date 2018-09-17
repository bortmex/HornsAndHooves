package ru.javaproject.hornsandhooves.service;

import ru.javaproject.hornsandhooves.model.Department;
import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

public interface DepartmentService {

    Department save(Department department);

    void delete(int id) throws NotFoundException;

    Department get(int id) throws NotFoundException;

    List<Department> getAll();

    void update(Department department);
}
