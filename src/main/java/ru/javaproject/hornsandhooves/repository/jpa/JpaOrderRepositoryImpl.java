package ru.javaproject.hornsandhooves.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.hornsandhooves.model.Order;
import ru.javaproject.hornsandhooves.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaOrderRepositoryImpl implements OrderRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @OrderBy(value = "id")
    @Transactional
    public Order save(Order order) {
        if (order.isNew()) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    public Order get(int id) {
        return em.find(Order.class, id);
    }

    @Override
    @OrderBy(value = "id")
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Order.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.ALL, Order.class).getResultList();
    }
}
