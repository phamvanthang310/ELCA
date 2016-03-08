/*
 * DummyDBStore
 * 
 * Project: Training
 * 
 * Copyright 2015 by ELCA
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of ELCA. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license
 * agreement you entered into with ELCA.
 */
package vn.elca.training.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vtp
 *
 */
public abstract class DummyDBStore {
    private static List<Project> projects = new ArrayList<Project>();
    private static List<Group> groups = new ArrayList<Group>();
    private static List<Employee> employees = new ArrayList<Employee>();

    public static List<Project> getProjects() {
        return projects;
    }

    public static List<Group> getGroups() {
        return groups;
    }

    public static List<Employee> getEmployees() {
        return employees;
    }
}
