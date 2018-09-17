package ru.javaproject.hornsandhooves;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.web.employee.EmployeesController;

import java.util.Arrays;
import java.util.List;


public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext()) {
            appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();

            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            EmployeesController adminUserController = appCtx.getBean(EmployeesController.class);

            List<Employee> employees = adminUserController.getAll();
            System.out.println(employees);

        }
    }
}