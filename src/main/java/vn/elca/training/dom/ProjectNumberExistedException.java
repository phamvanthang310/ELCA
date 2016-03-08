package vn.elca.training.dom;

public class ProjectNumberExistedException extends Exception {
    private long projectIdExisted;
    /**
     * 
     */
    private static final long serialVersionUID = -7524844128390013546L;

    public ProjectNumberExistedException(String msg) {
        super(msg);
    }

    public ProjectNumberExistedException(String msg, int existedId) {
        super(msg);
        this.projectIdExisted = existedId;
    }

    public long getProjectIdExisted() {
        return projectIdExisted;
    }

    public void setProjectIdExisted(int projectIdExisted) {
        this.projectIdExisted = projectIdExisted;
    }
}
