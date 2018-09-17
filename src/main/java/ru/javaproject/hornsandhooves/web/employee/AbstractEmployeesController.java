package ru.javaproject.hornsandhooves.web.employee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.service.EmployeesService;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNew;


public abstract class AbstractEmployeesController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected EmployeesService service;

    public List<Employee> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Employee get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public Employee create(Employee employee) {
        checkNew(employee);
        log.info("create " + employee);
        return service.save(employee);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(Employee employee, int id) {
        checkIdConsistent(employee, id);
        log.info("update " + employee);
        service.update(employee);
    }
}