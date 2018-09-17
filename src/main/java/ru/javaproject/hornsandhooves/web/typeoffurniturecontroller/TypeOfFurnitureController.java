package ru.javaproject.hornsandhooves.web.typeoffurniturecontroller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javaproject.hornsandhooves.model.TypeOfFurniture;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(TypeOfFurnitureController.REST_URL)
public class TypeOfFurnitureController extends AbstractTypeOfFurnitureController {
    static final String REST_URL = "/typeoffurnitures";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TypeOfFurniture> getAll() {
        return super.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TypeOfFurniture get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@Valid TypeOfFurniture employee) {

        if (employee.isNew()) {
            super.create(employee);
        } else {
            super.update(employee, employee.getId());
        }
    }
}
