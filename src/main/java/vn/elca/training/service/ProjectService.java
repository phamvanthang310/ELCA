package vn.elca.training.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.elca.training.dao.ICustomRepository;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.dom.ProjectNumberExistedException;
import vn.elca.training.dom.ProjectNumberNotFoundException;
import vn.elca.training.dom.VisaNotValidException;

@Service
public class ProjectService implements IProjectService {
    @Autowired
    private ICustomRepository projectRepository;

    @Override
    public List<Project> findAll() {
        List<Project> projects = projectRepository.findAll();
        Comparator<Project> compare = new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getNumber() - o2.getNumber();
            }
        };
        Collections.sort(projects, compare);
        return projects;
    }

    @Override
    public List<Project> findByName(String prjName) {
        return projectRepository.findByName(prjName);
    }

    @Override
    public Project findByNumber(int id) throws ProjectNumberNotFoundException {
        Project project = projectRepository.findByNumber(id);
        if (project == null) {
            throw new ProjectNumberNotFoundException("Project Number not found", id);
        }
        return project;
    }

    @Override
    public void updateProject(Project project, String member) throws VisaNotValidException {
        List<String> invalidVisa = invalidEmployee(member);
        if (invalidVisa.isEmpty()) {
            List<String> visas = inputEmpployees(member);
            List<Employee> employees = new ArrayList<Employee>();
            for (String visa : visas) {
                employees.add(projectRepository.findEmployeeByVisa(visa));
            }
            project.setEmployees(employees);
            projectRepository.updateProject(project);
        } else {
            throw new VisaNotValidException("Visa not valid", invalidVisa);
        }
    }

    @Override
    public void addNewProject(Project project, String input) throws ProjectNumberExistedException,
            VisaNotValidException {
        Project existedProject;
        try {
            existedProject = findByNumber(project.getNumber());
            throw new ProjectNumberExistedException(
                    "The project number already existed. Please select a different project numbers",
                    existedProject.getNumber());
        } catch (ProjectNumberNotFoundException e) {
            List<String> invalidVisa = invalidEmployee(input);
            if (invalidVisa.isEmpty()) {
                List<String> visas = inputEmpployees(input);
                List<Employee> employees = new ArrayList<Employee>();
                for (String visa : visas) {
                    employees.add(projectRepository.findEmployeeByVisa(visa));
                }
                project.setEmployees(employees);
                projectRepository.addNewProject(project);
            } else {
                if (!invalidVisa.isEmpty()) {
                    throw new VisaNotValidException("Visa not valid", invalidVisa);
                }
            }
        }
    }

    private List<String> invalidEmployee(String input) {
        List<String> invalidVisa = new ArrayList<String>();
        if (!input.isEmpty()) {
            List<String> visas = inputEmpployees(input);
            for (String visa : visas) {
                if (projectRepository.findEmployeeByVisa(visa.trim()) == null) {
                    invalidVisa.add(visa.trim());
                }
            }
        }
        return invalidVisa;
    }

    private List<String> inputEmpployees(String input) {
        String[] visaArray = input.split(", ");
        return Arrays.asList(visaArray);
    }

    @Override
    public void deleteProjectById(Long id) {
        projectRepository.deleteProjectById(id);
    }

    @Override
    public List<Group> findAllGroup() {
        List<Group> groups = projectRepository.findAllGroup();
        Comparator<Group> compare = new Comparator<Group>() {
            @Override
            public int compare(Group o1, Group o2) {
                return o1.getGroupLeader().getVisa().compareTo(o2.getGroupLeader().getVisa());
            }
        };
        Collections.sort(groups, compare);
        return groups;
    }

    @Override
    public Set<Project> searchProject(String query, String status) {
        Set<Project> projects = new TreeSet<Project>();
        List<Project> byCustomer = projectRepository.findByCustomer(query);
        List<Project> byName = projectRepository.findByName(query);
        projects.addAll(byCustomer);
        projects.addAll(byName);
        if (isInteger(query)) {
            Project byNumber = projectRepository.findByNumber(Integer.parseInt(query));
            if (byNumber != null) {
                projects.add(byNumber);
            }
        }
        if (!status.isEmpty()) {
            Iterator<Project> iter = projects.iterator();
            while (iter.hasNext()) {
                Project temp = iter.next();
                if (!temp.getStatus().equals(status)) {
                    iter.remove();
                }
            }
        }
        return projects;
    }

    private boolean isInteger(String query) {
        try {
            Integer.parseInt(query);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
