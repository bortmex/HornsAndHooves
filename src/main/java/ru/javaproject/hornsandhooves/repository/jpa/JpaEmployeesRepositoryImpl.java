package ru.javaproject.hornsandhooves.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.hornsandhooves.model.Employee;
import ru.javaproject.hornsandhooves.repository.EmployeesRepository;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaEmployeesRepositoryImpl implements EmployeesRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @OrderBy(value = "id")
    @Transactional
    public Employee save(Employee employee) {
        if (employee.isNew()) {
            em.persist(employee);
            return employee;
        } else {
            return em.merge(employee);
        }
    }

    @Override
    public Employee get(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    @OrderBy(value = "id")
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Employee.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public List<Employee> getAll() {
        return em.createNamedQuery(Employee.ALL_SORTED, Employee.class).getResultList();
    }
}
