package vn.elca.training.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;

@Repository
public interface ICustomRepository {
    List<Project> findAll();

    List<Project> findByName(String prjName);

    List<Project> findByCustomer(String customer);

    Project findByNumber(long id);

    void updateProject(Project project);

    void addNewProject(Project project);

    Employee findEmployeeByVisa(String visa);

    void deleteProjectById(Long id);

    List<Group> findAllGroup();

    List<Project> findByStatus(String status);
}
