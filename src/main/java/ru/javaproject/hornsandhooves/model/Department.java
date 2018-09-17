package ru.javaproject.hornsandhooves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Department.ALL, query = "SELECT d FROM Department d ORDER BY d.id"),
        @NamedQuery(name = Department.DELETE, query = "DELETE FROM Department d WHERE d.id=:id")
})
@Entity
@Table(name = "departments")
@Access(AccessType.FIELD)
public class Department extends NamedEntity{

    public static final String DELETE = "Department.delete";
    public static final String ALL = "Department.getAllSorted";

    public Department() {
    }

    public Department(Integer id, String name) {
        super(id, name);
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="department__id", referencedColumnName="id")
    private Set<TypeOfFurniture> typeOfFurniture;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "department")
    private Set<Employee> employees;

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<TypeOfFurniture> getTypeOfFurniture() {
        return typeOfFurniture;
    }

    public void setTypeOfFurniture(Set<TypeOfFurniture> typeOfFurniture) {
        this.typeOfFurniture = typeOfFurniture;
    }
}
