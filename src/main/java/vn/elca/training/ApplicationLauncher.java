package vn.elca.training;

import java.util.Date;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import vn.elca.training.dom.DummyDBStore;
import vn.elca.training.dom.Employee;
import vn.elca.training.dom.Group;
import vn.elca.training.dom.Project;
import vn.elca.training.service.IProjectService;
import vn.elca.training.web.ApplicationController;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = { ApplicationLauncher.class, ApplicationController.class, IProjectService.class })
@PropertySource({ "classpath:/application.properties", "classpath:/message_en.properties",
        "classpath:/message_fr.properties" })
public class ApplicationLauncher extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        prepareDummyData();
        SpringApplication.run(ApplicationLauncher.class, args);
    }

    private static void prepareDummyData() {
        Employee employee1 = new Employee("VTP", "Thang", "Pham");
        Employee employee2 = new Employee("COH", "Oai", "Ha");
        Employee employee3 = new Employee("NQT", "Quy", "Tran");
        Employee employee4 = new Employee("LAT", "Anh", "Tran");
        Employee employee5 = new Employee("VPT", "Phu", "Tang");
        Group group1 = new Group(0, employee3);
        Group group2 = new Group(0, employee2);
        Group group3 = new Group(0, employee4);
        Project project1 = new Project(group1, 1001, "Mobile Dating Apps", "NTT data", "NEW", new Date());
        project1.getEmployees().add(employee1);
        Project project2 = new Project(group2, 1002, "AntiThief Devices", "FPT Telecome", "PLA", new Date());
        Project project3 = new Project(group1, 1003, "Spring MVC", "Kanden Japan", "INP", new Date());
        Project project4 = new Project(group3, 1004, "Hibernate", "Elca.ltd", "FIN", new Date());
        DummyDBStore.getEmployees().add(employee1);
        DummyDBStore.getEmployees().add(employee2);
        DummyDBStore.getEmployees().add(employee3);
        DummyDBStore.getEmployees().add(employee4);
        DummyDBStore.getEmployees().add(employee5);
        DummyDBStore.getGroups().add(group1);
        DummyDBStore.getGroups().add(group2);
        DummyDBStore.getGroups().add(group3);
        DummyDBStore.getProjects().add(project2);
        DummyDBStore.getProjects().add(project4);
        DummyDBStore.getProjects().add(project1);
        DummyDBStore.getProjects().add(project3);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public static MessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("message");
        return resourceBundleMessageSource;
    }

    @Bean
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        resolver.setCookieName("language");
        resolver.setCookieMaxAge(4800);
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
