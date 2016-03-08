package vn.elca.training.service;

import java.util.List;
import java.util.Set;

import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.dom.ProjectNumberExistedException;
import vn.elca.training.dom.ProjectNumberNotFoundException;
import vn.elca.training.dom.VisaNotValidException;

public interface IProjectService {
    List<Project> findAll();

    List<Project> findByName(String prjName);

    Project findByNumber(int id) throws ProjectNumberNotFoundException;

    void updateProject(Project project, String member) throws VisaNotValidException;

    void addNewProject(Project project, String visa) throws ProjectNumberExistedException, VisaNotValidException;

    void deleteProjectById(Long id);

    List<Group> findAllGroup();

    Set<Project> searchProject(String query, String status);
}
