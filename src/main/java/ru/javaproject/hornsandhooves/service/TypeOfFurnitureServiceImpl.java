package ru.javaproject.hornsandhooves.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;
import ru.javaproject.hornsandhooves.repository.TypeOfFurnitureRepository;
import ru.javaproject.hornsandhooves.util.exception.NotFoundException;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNotFoundWithId;

@Service
public class TypeOfFurnitureServiceImpl implements TypeOfFurnitureService {

    @Autowired
    private TypeOfFurnitureRepository repository;

    @Override
    public TypeOfFurniture save(TypeOfFurniture typeOfFurniture) {
        return repository.save(typeOfFurniture);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public TypeOfFurniture get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public List<TypeOfFurniture> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(TypeOfFurniture typeOfFurniture) {
        Assert.notNull(typeOfFurniture, "typeOfFurniture must not be null");
        checkNotFoundWithId(repository.save(typeOfFurniture), typeOfFurniture.getId());
    }
}
