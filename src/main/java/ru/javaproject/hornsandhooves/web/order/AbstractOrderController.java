package ru.javaproject.hornsandhooves.web.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.service.OrderService;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNew;

public abstract class AbstractOrderController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractOrderController.class);

    @Autowired
    private OrderService service;

    public Order get(int id){
        LOG.info("get order {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete order {}", id);
        service.delete(id);
    }

    public Order create(Order order){
        checkNew(order);
        LOG.info("create order");
        return service.save(order);
    }

    public void update(Order order, int id) {
        checkIdConsistent(order, id);
        LOG.info("update {} for Order {}", order);
        service.update(order);
    }

    public void update(Order order) {
        LOG.info("update {}", order);
        service.update(order);
    }


    public List<Order> getAll(){
        LOG.info("getAll() Order");
        return service.getAll();
    }
}
