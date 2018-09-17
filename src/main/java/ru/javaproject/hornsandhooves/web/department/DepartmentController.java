package ru.javaproject.hornsandhooves.web.department;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.hornsandhooves.model.Department;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(DepartmentController.REST_URL)
public class DepartmentController extends AbstractDepartmentController {
    static final String REST_URL = "/departments";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Department> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Department get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid Department employee) {

        if (employee.isNew()) {
            super.create(employee);
        } else {
            super.update(employee, employee.getId());
        }
    }
}
