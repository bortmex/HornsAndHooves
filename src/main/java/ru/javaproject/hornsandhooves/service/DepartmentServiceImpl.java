package ru.javaproject.hornsandhooves.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaproject.hornsandhooves.model.Department;
import ru.javaproject.hornsandhooves.repository.DepartmentRepository;
import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department save(Department department) {
        return repository.save(department);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Department get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Department> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Department department) {
        Assert.notNull(department, "department must not be null");
        checkNotFoundWithId(repository.save(department), department.getId());
    }
}
