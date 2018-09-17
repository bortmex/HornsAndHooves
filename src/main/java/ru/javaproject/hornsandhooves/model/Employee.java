package ru.javaproject.hornsandhooves.model;

import javax.persistence.*;

@SuppressWarnings("JpaQlInspection")
@NamedQueries({
        @NamedQuery(name = Employee.DELETE, query = "DELETE FROM Employee e WHERE e.id=:id"),
        @NamedQuery(name = Employee.ALL_SORTED, query = "SELECT e FROM Employee e ORDER BY e.name")
})
@Entity
@Table(name = "employees")
@Access(AccessType.FIELD)
public class Employee extends NamedEntity{

    public static final String DELETE = "Employee.delete";
    public static final String ALL_SORTED = "Employee.getAllSorted";

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="department_id")
    private Department department;

    @Column(name="status_work")
    private Boolean statusWork;

    public Employee() {
    }

    public Employee(Employee u) {
        this(u.getId(), u.getName(), u.getDepartment(), u.statusWork);
    }

    public Employee(Integer id, String name, Department department, Boolean statusWork) {
        super(id, name);
        this.department = department;
        this.statusWork = statusWork;
    }

    public Boolean getStatusWork() {
        return statusWork;
    }

    public void setStatusWork(Boolean statusWork) {
        this.statusWork = statusWork;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "department=" + department +
                ", statusWork=" + statusWork +
                '}';
    }
}
