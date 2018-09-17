package ru.javaproject.hornsandhooves.repository;

import ru.javaproject.hornsandhooves.model.TypeOfFurniture;

import java.util.List;

public interface TypeOfFurnitureRepository {
    TypeOfFurniture save(TypeOfFurniture typeOfFurniture);

    boolean delete(int id);

    TypeOfFurniture get(int id);

    List<TypeOfFurniture> getAll();
}
