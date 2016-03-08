package vn.elca.training.web;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Project;
import vn.elca.training.dom.ProjectNumberExistedException;
import vn.elca.training.dom.ProjectNumberNotFoundException;
import vn.elca.training.dom.VisaNotValidException;
import vn.elca.training.service.IProjectService;

@Controller
@SessionAttributes({ "query", "status" })
public class ApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
    @Autowired
    private IProjectService projectService;
    @Value("${total.number.of.projects}")
    private String message;
    @Value("${message.unexpected.error}")
    private String unexpectedError;
    @Value("${message.invalid.visa}")
    private String visaError;
    @Value("${message.existed.project.number}")
    private String projectError;

    @ModelAttribute
    void addMessage(Model model) {
        model.addAttribute("message", String.format(message, projectService.findAll().size()));
        model.addAttribute("title", "Project Management Tool");
        model.addAttribute("groups", projectService.findAllGroup());
        if (!model.containsAttribute("query")) {
            model.addAttribute("query", "");
        }
        if (!model.containsAttribute("status")) {
            model.addAttribute("status", "");
        }
    }

    // @RequestMapping("/")
    // ModelAndView main(@ModelAttribute("query") String query) {
    // Map<String, Object> model = new HashMap<String, Object>() {
    // private static final long serialVersionUID = -6883088231537577238L;
    // {
    // // put("title", "Project Management Tool");
    // // put("message", String.format(message, projectService.findAll().size()));
    // }
    // };
    // model.put("query", query);
    // return new ModelAndView("list", model);
    // }
    @RequestMapping("/search")
    @ResponseBody
    Set<Project> query(@RequestParam("query") String query, @RequestParam("status") String status, Model model) {
        model.addAttribute("query", query);
        model.addAttribute("status", status);
        return projectService.searchProject(query, status);
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    String projectDetail(@PathVariable("id") int id, Model model) {
        try {
            Project project = projectService.findByNumber(id);
            model.addAttribute("project", projectService.findByNumber(id));
            String members = "";
            for (Employee employee : project.getEmployees()) {
                if (employee != null) {
                    members += employee.getVisa() + ", ";
                }
            }
            model.addAttribute("members", members);
        } catch (ProjectNumberNotFoundException e) {
            String msg = "Project Number not found: " + e.getProjectNumber();
            logger.error(e.getMessage());
            model.addAttribute("Error", String.format(unexpectedError, msg));
            return "error";
        }
        return "detail";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    String saveProject(@ModelAttribute Project project, BindingResult result, Model model, String member) {
        if (result.hasErrors()) {
            // TODO [VTP / Sprint2]
        }
        try {
            projectService.updateProject(project, member);
        } catch (VisaNotValidException e) {
            logger.error(e.getMessage());
            model.addAttribute("visaError", String.format(visaError, e.getInvalidVisa().toString()));
            return "detail";
        }
        return "redirect:";
    }

    @RequestMapping("/new")
    String newProject(Model model) {
        // model.addAttribute("groups", projectService.findAllGroup());
        return "new";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String addNewProject(@ModelAttribute Project project, BindingResult result, Model model,
            @RequestParam("member") String member) {
        String view = "list";
        if (result.hasErrors()) {
            // TODO [VTP / Sprint2]
        }
        try {
            projectService.addNewProject(project, member);
        } catch (ProjectNumberExistedException e) {
            logger.error(e.getMessage());
            model.addAttribute("projectError", String.format(projectError, e.getProjectIdExisted()));
            view = "new";
        } catch (VisaNotValidException e) {
            logger.error(e.getMessage());
            model.addAttribute("visaError", String.format(visaError, e.getInvalidVisa().toString()));
            view = "new";
        }
        return view;
    }

    @RequestMapping("/")
    String viewProjectList(Model model, @ModelAttribute("query") String query) {
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("query", query);
        // System.out.println(query);
        return "list";
    }

    // @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    // String deleteProject(@PathVariable("id") long id) {
    // projectService.deleteProjectById(id);
    // return "redirect:/";
    // }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    String deleteMultiProject(@RequestParam("id") long id) {
        projectService.deleteProjectById(id);
        return "";
    }
}
