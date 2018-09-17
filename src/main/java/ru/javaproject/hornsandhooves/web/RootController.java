package ru.javaproject.hornsandhooves.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javaproject.hornsandhooves.service.DepartmentService;
import ru.javaproject.hornsandhooves.service.EmployeesService;
import ru.javaproject.hornsandhooves.web.employee.AbstractEmployeesController;

@Controller
public class RootController extends AbstractEmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public String root() {
        return "index";
    }
/*
    @GetMapping("/employees")
    public String getAllEmployees(Model model){
        model.addAttribute("allDepartment", departmentService.getAll());
        return "employeess";
    }

    @GetMapping("/orders")
    public String getAllOrders(){
        return "orders";
    }*/
}