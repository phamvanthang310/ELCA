package vn.elca.training.dom;

import java.util.List;

public class VisaNotValidException extends Exception {
    /**
     * 
     */
    private List<String> invalidVisa;

    public List<String> getInvalidVisa() {
        return invalidVisa;
    }

    public void setInvalidVisa(List<String> invalidVisa) {
        this.invalidVisa = invalidVisa;
    }

    private static final long serialVersionUID = -6300595154988509943L;

    public VisaNotValidException(String msg, List<String> invalidVisa) {
        super(msg);
        this.invalidVisa = invalidVisa;
    }

    public VisaNotValidException(String msg) {
        super(msg);
    }
}
