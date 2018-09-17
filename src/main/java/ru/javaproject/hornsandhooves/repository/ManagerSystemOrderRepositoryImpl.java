package ru.javaproject.hornsandhooves.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import ru.javaproject.hornsandhooves.model.*;
import ru.javaproject.hornsandhooves.service.DepartmentService;
import ru.javaproject.hornsandhooves.service.EmployeesService;
import ru.javaproject.hornsandhooves.service.OrderService;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ManagerSystemOrderRepositoryImpl{

    private static final Logger log = LoggerFactory.getLogger(ManagerSystemOrderRepositoryImpl.class);

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmployeesService employeesService;

    private ArrayDeque<Order> ordersPending = new ArrayDeque<>();
    private Map<Order, List<Employee>> mapOrderForEmployees = new HashMap<>();

    public ManagerSystemOrderRepositoryImpl() {
    }

    @PostConstruct
    @Scheduled(fixedRate = 1000)
    public void system() {
        log.info("The time is now");

        for (Order order : orderService.getAll()) {
            if (order.getStatus().equals(Status.STATUS_PENDING)) ordersPending.add(order);
        }
        List<Employee> allEmployees = employeesService.getAll();
        Map<Department, List<Employee>> mapDepEmp = new HashMap<>();
        mapDepEmp.putAll(updateEmp(allEmployees));

        for (Order order : ordersPending) {
            if (order.getStatus().equals(Status.STATUS_PENDING)) {
                TypeOfFurniture typeOfFurniture = order.getTypeOfFurniture();
                Department department = departmentService.get(typeOfFurniture.getDepartment());
                List<Employee> allFreeEmp = new ArrayList<>();
                if (mapDepEmp.get(department) != null) {
                    allFreeEmp.addAll(mapDepEmp.get(department));
                    if (allFreeEmp.size() > 2) {
                        ordersPending.removeFirstOccurrence(order);
                        List<Employee> listEmp = updateEmployees(allFreeEmp, employeesService, 3);
                        order.setEndOrder(order.getCreateOrder().plusSeconds(typeOfFurniture.getNumberofsecondsperformedbyoneperson() / 3));
                        order.setActualDeadline(typeOfFurniture.getNumberofsecondsperformedbyoneperson() / 3);
                        order.setStatus(Status.STATUS_INTHEWORK_DEPARTMENT);
                        orderService.update(order);
                        mapOrderForEmployees.put(order, listEmp);
                    } else {
                        ordersPending.removeFirstOccurrence(order);
                        Employee employee = updateEmployees(allFreeEmp, employeesService, 1).get(0);
                        order.setEndOrder(order.getCreateOrder().plusSeconds(typeOfFurniture.getNumberofsecondsperformedbyoneperson()));
                        order.setActualDeadline(typeOfFurniture.getNumberofsecondsperformedbyoneperson());
                        List<Employee> listEmp = new ArrayList<>();
                        listEmp.add(employee);
                        order.setStatus(Status.STATUS_INTHEWORK_PERSON);
                        orderService.update(order);
                        mapOrderForEmployees.put(order, listEmp);
                    }
                }
            }
            mapDepEmp.clear();
            mapDepEmp.putAll(updateEmp(allEmployees));
        }

        List<Order> removeOrder = new ArrayList<>();
        for (Map.Entry<Order, List<Employee>> entry : mapOrderForEmployees.entrySet()) {
            Order order = entry.getKey();
            List<Employee> listEmployees = entry.getValue();
            if(listEmployees==null){
                order.setStatus(Status.STATUS_PENDING);
                ordersPending.addFirst(order);
                removeOrder.add(order);
                continue;
            }else if((listEmployees.size()==1 || listEmployees.size()==2) && order.getStatus()==Status.STATUS_INTHEWORK_DEPARTMENT){
                TypeOfFurniture typeOfFurniture = order.getTypeOfFurniture();
                Department department = departmentService.get(typeOfFurniture.getDepartment());
                List<Employee> allFreeEmp = mapDepEmp.get(department);
                if(allFreeEmp.size()>1 && listEmployees.size()==1)
                    updateEmployees(allEmployees, employeesService, 2);
                else if(allFreeEmp.size()==1)
                    updateEmployees(allEmployees, employeesService, 1);
            }
            order.setActualDeadline(order.getActualDeadline() - 5);
            if (order.getActualDeadline() <= 0) {
                order.setStatus(Status.STATUS_FINISHED);
                orderService.update(order);
                for (Employee employee :listEmployees) {
                    employee.setStatusWork(false);
                    employeesService.update(employee);
                }
            }
        }

        for (Order order: removeOrder) {
            mapOrderForEmployees.remove(order);
        }
    }

    private static List<Employee> updateEmployees(List<Employee> list, EmployeesService employeesService, int count){
        List<Employee> listEmp = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            listEmp.add(list.get(i));
            listEmp.get(i).setStatusWork(true);
            employeesService.update(listEmp.get(i));
        }
        return listEmp;
    }

    private static Map<Department, List<Employee>> updateEmp(List<Employee> allEmployees){
        Map<Department, List<Employee>> mapDepEmp = new HashMap<>();
        for (Employee employee : allEmployees) {
            if (!employee.getStatusWork()) {
                mapDepEmp.computeIfAbsent(employee.getDepartment(), k -> new ArrayList<>());
                mapDepEmp.get(employee.getDepartment()).add(employee);
            }
        }
        return mapDepEmp;
    }

    public ArrayDeque<Order> getOrdersPending() {
        return ordersPending;
    }

    public void setOrdersPending(ArrayDeque<Order> ordersPending) {
        this.ordersPending = ordersPending;
    }

    public Map<Order, List<Employee>> getMapOrderForEmployees() {
        return mapOrderForEmployees;
    }

    public void setMapOrderForEmployees(Map<Order, List<Employee>> mapOrderForEmployees) {
        this.mapOrderForEmployees = mapOrderForEmployees;
    }
}
