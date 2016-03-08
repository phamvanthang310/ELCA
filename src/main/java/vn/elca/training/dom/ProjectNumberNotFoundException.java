package vn.elca.training.dom;

public class ProjectNumberNotFoundException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -8201200311985672094L;
    int projectNumber;

    public ProjectNumberNotFoundException(int id) {
        super();
        this.projectNumber = id;
    }

    public ProjectNumberNotFoundException(String msg, int projectNumber) {
        super(msg);
        this.projectNumber = projectNumber;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }
}
