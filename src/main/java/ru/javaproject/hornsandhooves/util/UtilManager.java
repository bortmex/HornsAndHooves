package ru.javaproject.hornsandhooves.util;

import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilManager {

    public static List<Order> getAllPendingOrders(Map<Order, List<Employee>> mapOrders){
        List<Order> list = new ArrayList<>();
        for (Map.Entry<Order, List<Employee>> map:mapOrders.entrySet()) {
            if(map.getKey().getStatus()== Status.STATUS_INTHEWORK_DEPARTMENT || map.getKey().getStatus()== Status.STATUS_INTHEWORK_PERSON)
                list.add(map.getKey());
        }
        return list;
    }

    public static List<Order> getAllIntheworkPersonOrders(Map<Order, List<Employee>> mapOrders){
        List<Order> list = new ArrayList<>();
        for (Map.Entry<Order, List<Employee>> map:mapOrders.entrySet()) {
            if(map.getKey().getStatus()== Status.STATUS_INTHEWORK_PERSON)
                list.add(map.getKey());
        }
        return list;
    }

    public static List<Order> getAllIntheworkDepartmentOrders(Map<Order, List<Employee>> mapOrders){
        List<Order> list = new ArrayList<>();
        for (Map.Entry<Order, List<Employee>> map:mapOrders.entrySet()) {
            if(map.getKey().getStatus()== Status.STATUS_INTHEWORK_DEPARTMENT)
                list.add(map.getKey());
        }
        return list;
    }
}
