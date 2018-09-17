package ru.javaproject.hornsandhooves.web.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.service.DepartmentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(EmployeesController.REST_URL)
public class EmployeesController extends AbstractEmployeesController {
    static final String REST_URL = "/employees";

    @Autowired
    private DepartmentService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping("{depId}")
    public void createOrUpdate(@PathVariable (value = "depId") Integer depId, @Valid Employee employee) {

        employee.setDepartment(service.get(depId));
        if (employee.isNew()) {
            super.create(employee);
        } else {
            super.update(employee, employee.getId());
        }
    }
}