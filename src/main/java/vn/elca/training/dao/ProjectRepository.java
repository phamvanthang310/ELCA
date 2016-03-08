package vn.elca.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.elca.training.dom.DummyDBStore;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;

@Repository
public class ProjectRepository implements ICustomRepository {
    public ProjectRepository() {
    }

    public List<Project> findAll() {
        return DummyDBStore.getProjects();
    }

    public List<Project> findByName(String prjName) {
        List<Project> result = new ArrayList<Project>();
        for (Project project : DummyDBStore.getProjects()) {
            if (project.getName().toUpperCase().contains(prjName.toUpperCase())) {
                result.add(project);
            }
        }
        return result;
    }

    public Group findGroupByVisa(String visa) {
        for (Group group : DummyDBStore.getGroups()) {
            if (group.getGroupLeader().getVisa().equals(visa)) {
                return group;
            }
        }
        return null;
    }

    public Project findByNumber(long id) {
        for (Project project : DummyDBStore.getProjects()) {
            if (project.getNumber() == id) {
                return project;
            }
        }
        return null;
    }

    public void updateProject(Project project) {
        Project newProject = findByNumber(project.getNumber());
        newProject.setGroup(findGroupByVisa(project.getGroup().getGroupLeader().getVisa()));
        newProject.setFinishingDate(project.getFinishingDate());
        newProject.setStartDate(project.getStartDate());
        newProject.setName(project.getName());
        newProject.setCustomer(project.getCustomer());
        newProject.setStatus(project.getStatus());
        newProject.setEmployees(project.getEmployees());
    }

    @Override
    public void addNewProject(Project project) {
        DummyDBStore.getProjects().add(project);
    }

    @Override
    public Employee findEmployeeByVisa(String visa) {
        for (Employee employee : DummyDBStore.getEmployees()) {
            if (employee.getVisa().equals(visa)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void deleteProjectById(Long id) {
        DummyDBStore.getProjects().remove(findByNumber(id));
    }

    @Override
    public List<Group> findAllGroup() {
        return DummyDBStore.getGroups();
    }

    @Override
    public List<Project> findByCustomer(String customer) {
        List<Project> projects = new ArrayList<Project>();
        for (Project project : DummyDBStore.getProjects()) {
            if (project.getCustomer().contains(customer)) {
                projects.add(project);
            }
        }
        return projects;
    }

    @Override
    public List<Project> findByStatus(String status) {
        List<Project> projects = new ArrayList<Project>();
        for (Project project : DummyDBStore.getProjects()) {
            if (project.getStatus().equals(status)) {
                projects.add(project);
            }
        }
        return projects;
    }
}
