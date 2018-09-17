package ru.javaproject.hornsandhooves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.repository.OrderRepository;
import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNotFoundWithId;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Order get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Order order) {
        Assert.notNull(order, "order must not be null");
        checkNotFoundWithId(repository.save(order), order.getId());
    }
}
