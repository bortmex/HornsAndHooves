package ru.javaproject.hornsandhooves.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;
import ru.javaproject.hornsandhooves.repository.TypeOfFurnitureRepository;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaTypeOfFurnitureRepositoryImpl implements TypeOfFurnitureRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    @OrderBy(value = "id")
    @Transactional
    public TypeOfFurniture save(TypeOfFurniture typeOfFurniture) {
        if (typeOfFurniture.isNew()) {
            em.persist(typeOfFurniture);
            return typeOfFurniture;
        } else {
            return em.merge(typeOfFurniture);
        }
    }

    @Override
    public TypeOfFurniture get(int id) {
        return em.find(TypeOfFurniture.class, id);
    }

    @Override
    @OrderBy(value = "id")
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(TypeOfFurniture.DELETE).setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public List<TypeOfFurniture> getAll() {
        return em.createNamedQuery(TypeOfFurniture.ALL, TypeOfFurniture.class).getResultList();
    }
}
