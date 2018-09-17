package ru.javaproject.hornsandhooves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.repository.EmployeesRepository;
import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNotFoundWithId;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    @Autowired
    private EmployeesRepository repository;

    @Override
    public Employee save(Employee employee) {
        Assert.notNull(employee, "employee must not be null");
        return repository.save(employee);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Employee get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Employee> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Employee employee) {
        Assert.notNull(employee, "employee must not be null");
        checkNotFoundWithId(repository.save(employee), employee.getId());
    }

}
