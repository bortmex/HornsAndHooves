package ru.javaproject.hornsandhooves.web.typeoffurniturecontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;
import ru.javaproject.hornsandhooves.service.TypeOfFurnitureService;

import java.util.List;

import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkIdConsistent;
import static ru.javaproject.hornsandhooves.util.ValidationUtil.checkNew;

public abstract class AbstractTypeOfFurnitureController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTypeOfFurnitureController.class);

    @Autowired
    private TypeOfFurnitureService service;

    public TypeOfFurniture get(int id){
        LOG.info("get typeoffurniture {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        LOG.info("delete typeoffurniture {}", id);
        service.delete(id);
    }

    public TypeOfFurniture create(TypeOfFurniture typeoffurniture){
        checkNew(typeoffurniture);
        LOG.info("create typeoffurniture");
        return service.save(typeoffurniture);
    }

    public void update(TypeOfFurniture typeoffurniture, int id) {
        checkIdConsistent(typeoffurniture, id);
        LOG.info("update {} for Typeoffurniture {}", typeoffurniture);
        service.update(typeoffurniture);
    }

    public void update(TypeOfFurniture typeoffurniture) {
        LOG.info("update {}", typeoffurniture);
        service.update(typeoffurniture);
    }


    public List<TypeOfFurniture> getAll(){
        LOG.info("getAll() Typeoffurniture");
        return service.getAll();
    }
}
