package ru.javaproject.hornsandhooves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = TypeOfFurniture.ALL, query = "SELECT tof FROM TypeOfFurniture tof ORDER BY tof.id"),
       /* @NamedQuery(name = TypeOfFurniture.UPDATE, query = "UPDATE Product p SET p.name=:name, p.price=:price, p.description=:description WHERE p.id=:id"),*/
        @NamedQuery(name = TypeOfFurniture.DELETE, query = "DELETE FROM TypeOfFurniture tof WHERE tof.id=:id")
})
@Entity
@Table(name = "typeoffurnitures")
@Access(AccessType.FIELD)
public class TypeOfFurniture extends NamedEntity{

    public static final String DELETE = "TypeOfFurniture.delete";
    public static final String ALL = "TypeOfFurniture.getAllSorted";

    public TypeOfFurniture() {
    }

    public TypeOfFurniture(Integer id, String name, Integer leadtimeindays) {
        super(id, name);
        this.numberofsecondsperformedbyoneperson = leadtimeindays;
    }

    @Column(name="department__id")
    private Integer department;

    @Column(name = "number_of_seconds_performed_by_one_person")
    private Integer numberofsecondsperformedbyoneperson;

    @JsonIgnore
    @OneToMany(mappedBy = "typeOfFurniture", fetch = FetchType.EAGER)
    private Set<Order> orders;

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Integer getNumberofsecondsperformedbyoneperson() {
        return numberofsecondsperformedbyoneperson;
    }

    public void setNumberofsecondsperformedbyoneperson(Integer numberofsecondsperformedbyoneperson) {
        this.numberofsecondsperformedbyoneperson = numberofsecondsperformedbyoneperson;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "TypeOfFurniture{" +
                "department=" + department +
                ", leadtimeindays=" + numberofsecondsperformedbyoneperson +
                '}';
    }
}
