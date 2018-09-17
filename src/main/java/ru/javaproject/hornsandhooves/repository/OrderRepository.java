package ru.javaproject.hornsandhooves.repository;

import ru.javaproject.hornsandhooves.model.Order;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);

    boolean delete(int id);

    Order get(int id);

    List<Order> getAll();
}
