package ru.javaproject.hornsandhooves.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.hornsandhooves.model.Department;
import ru.javaproject.hornsandhooves.repository.DepartmentRepository;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaDepartmentRepositoryImpl implements DepartmentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @OrderBy(value = "id")
    @Transactional
    public Department save(Department department) {
        if (department.isNew()) {
            em.persist(department);
            return department;
        } else {
            return em.merge(department);
        }
    }

    @Override
    public Department get(int id) {
        return em.find(Department.class, id);
    }

    @Override
    @OrderBy(value = "id")
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Department.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public List<Department> getAll() {
        return em.createNamedQuery(Department.ALL, Department.class).getResultList();
    }
}
