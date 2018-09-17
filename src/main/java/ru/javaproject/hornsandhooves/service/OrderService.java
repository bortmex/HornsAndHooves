package ru.javaproject.hornsandhooves.service;

import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

public interface OrderService {
    Order save(Order order);

    void delete(int id) throws NotFoundException;

    Order get(int id) throws NotFoundException;

    List<Order> getAll();

    void update(Order order);
}
