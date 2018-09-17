package ru.javaproject.hornsandhooves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.repository.ManagerSystemOrderRepositoryImpl;

import java.util.List;
import java.util.Map;

@Service
public class ManagerSystemOrderServiceImpl implements ManagerSystemOrderService {

    @Autowired
    private ManagerSystemOrderRepositoryImpl repository;

    @Override
    public Map<Order, List<Employee>> getAllOrderForEmployees() {
        return repository.getMapOrderForEmployees();
    }
}
