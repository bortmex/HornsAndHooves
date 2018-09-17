package ru.javaproject.hornsandhooves.web.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.service.ManagerSystemOrderService;
import ru.javaproject.hornsandhooves.service.TypeOfFurnitureService;
import ru.javaproject.hornsandhooves.util.UtilManager;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(OrderController.REST_URL)
public class OrderController extends AbstractOrderController {
    static final String REST_URL = "/orders";

    @Autowired
    private TypeOfFurnitureService serviceTOF;

    @Autowired
    private ManagerSystemOrderService service;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/pending", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllPendingOrders() {
        return UtilManager.getAllPendingOrders(service.getAllOrderForEmployees());
    }

    @GetMapping(value = "/pendingintheworkperson", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllIntheworkPersonOrders() {
        return UtilManager.getAllIntheworkPersonOrders(service.getAllOrderForEmployees());
    }

    @GetMapping(value = "/pendingintheworkdepartment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllIntheworkDepartmentOrders() {
        return UtilManager.getAllIntheworkDepartmentOrders(service.getAllOrderForEmployees());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping("{typeId}")
    public void createOrUpdate(@PathVariable(value = "typeId") Integer typeId, @Valid Order order) {
        order.setTypeOfFurniture(serviceTOF.get(typeId));
        if (order.isNew()) {
            super.create(order);
        } else {
            super.update(order, order.getId());
        }
    }
}
