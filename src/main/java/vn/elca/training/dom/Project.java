package vn.elca.training.dom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Project implements Comparable<Project> {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(targetEntity = Group.class)
    private Group group;
    private int number;
    @Column(nullable = false)
    private String name;
    private String customer;
    private String status;
    @Column
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date finishingDate;
    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    public Project() {
    }

    public Project(Group group, int number, String name, String customer, String status, Date startDate,
            Date finishingDate) {
        this.group = group;
        this.number = number;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.finishingDate = finishingDate;
        this.employees = new ArrayList<Employee>();
    }

    public Project(Group group, int number, String name, String customer, String status, Date startDate) {
        this.group = group;
        this.number = number;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.employees = new ArrayList<Employee>();
    }

    public Project(Group group, int number, String name, String customer, String status, Date startDate,
            Date finishingDate, List<Employee> employees) {
        this.group = group;
        this.number = number;
        this.name = name;
        this.customer = customer;
        this.status = status;
        this.startDate = startDate;
        this.finishingDate = finishingDate;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(Date finishingDate) {
        this.finishingDate = finishingDate;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.status + ", " + this.id + ", " + this.startDate + ", " + this.finishingDate;
    }

    @Override
    public boolean equals(Object obj) {
        Project project = (Project) obj;
        return this.number == project.getNumber();
    }

    @Override
    public int compareTo(Project o) {
        return this.number - o.getNumber();
    }
}