package ru.javaproject.hornsandhooves.service;

import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.model.Order;

import java.util.List;
import java.util.Map;

public interface ManagerSystemOrderService {
    Map<Order, List<Employee>> getAllOrderForEmployees();
}
