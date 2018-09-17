package ru.javaproject.hornsandhooves.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Order.ALL, query = "SELECT ord FROM Order ord ORDER BY ord.id"),
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order ord WHERE ord.id=:id")
})
@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class Order extends NamedEntity{

    public static final String DELETE = "Order.delete";
    public static final String ALL = "Order.getAll";

    public Order() {
    }

    public Order(Integer id, String name, TypeOfFurniture typeOfFurniture, LocalDateTime createOrder, Status status, LocalDateTime endOrder, Integer actualDeadline) {
        super(id, name);
        this.typeOfFurniture = typeOfFurniture;
        this.createOrder = createOrder;
        this.status = status;
        this.endOrder = endOrder;
        this.actualDeadline = actualDeadline;
    }

    public Order(String name, TypeOfFurniture typeOfFurniture, LocalDateTime createOrder) {
        super(null, name);
        this.typeOfFurniture = typeOfFurniture;
        this.createOrder = createOrder;

    }

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="type_of_furniture_id", nullable = false)
    private TypeOfFurniture typeOfFurniture;

    @Column(name = "create_order")
    private LocalDateTime createOrder;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "end_order")
    private LocalDateTime endOrder;

    @Column(name = "actual_deadline")
    private Integer actualDeadline;


    public TypeOfFurniture getTypeOfFurniture() {
        return typeOfFurniture;
    }

    public void setTypeOfFurniture(TypeOfFurniture typeOfFurniture) {
        this.typeOfFurniture = typeOfFurniture;
    }

    public LocalDateTime getCreateOrder() {
        return createOrder;
    }

    public void setCreateOrder(LocalDateTime createOrder) {
        this.createOrder = createOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getEndOrder() {
        return endOrder;
    }

    public void setEndOrder(LocalDateTime endOrder) {
        this.endOrder = endOrder;
    }

    public Integer getActualDeadline() {
        return actualDeadline;
    }

    public void setActualDeadline(Integer actualDeadline) {
        this.actualDeadline = actualDeadline;
    }

    @Override
    public String toString() {
        return "Order{" +
                "typeOfFurniture=" + typeOfFurniture +
                ", createOrder=" + createOrder +
                ", status=" + status +
                ", endOrder=" + endOrder +
                ", actualDeadline=" + actualDeadline +
                '}';
    }
}
