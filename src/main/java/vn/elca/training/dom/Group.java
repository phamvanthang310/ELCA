package vn.elca.training.dom;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Group {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(targetEntity = Employee.class)
    private Employee groupLeader;

    public Group() {
    }

    public Group(long id, Employee groupLeader) {
        this.id = id;
        this.groupLeader = groupLeader;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Employee getGroupLeader() {
        return groupLeader;
    }

    public void setGroupLeader(Employee groupLeader) {
        this.groupLeader = groupLeader;
    }
}
