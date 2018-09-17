package ru.javaproject.hornsandhooves.repository;

import ru.javaproject.hornsandhooves.model.Department;

import java.util.List;

public interface DepartmentRepository {
    Department save(Department department);

    boolean delete(int id);

    Department get(int id);

    List<Department> getAll();
}
