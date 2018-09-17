package ru.javaproject.hornsandhooves.service;


import ru.javaproject.hornsandhooves.util.exception.NotFoundException;
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;

import java.util.List;

public interface TypeOfFurnitureService {
    TypeOfFurniture save(TypeOfFurniture typeOfFurniture);

    void delete(int id) throws NotFoundException;

    TypeOfFurniture get(int id) throws NotFoundException;

    List<TypeOfFurniture> getAll();

    void update(TypeOfFurniture typeOfFurniture);
}
