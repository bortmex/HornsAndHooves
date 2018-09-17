package ru.javaproject.hornsandhooves.web.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.hornsandhooves.model.Department;
import ru.javaproject.hornsandhooves.service.DepartmentService;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNew;

public abstract class AbstractDepartmentController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDepartmentController.class);

    @Autowired
    private DepartmentService service;

    public Department get(int id){
        LOG.info("get department {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete department {}", id);
        service.delete(id);
    }

    public Department create(Department department){
        checkNew(department);
        LOG.info("create department");
        return service.save(department);
    }

    public void update(Department department, int id) {
        checkIdConsistent(department, id);
        LOG.info("update {} for Partner {}", department);
        service.update(department);
    }

    public void update(Department department) {
        LOG.info("update {}", department);
        service.update(department);
    }


    public List<Department> getAll(){
        LOG.info("getAll() Department");
        return service.getAll();
    }
}
