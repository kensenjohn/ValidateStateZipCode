package com.validate;

/**
 * Created with IntelliJ IDEA.
 * User: kensen
 * Date: 1/12/13
 * Time: 11:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ValidateStatusBean {
    private boolean isValid = false;
    private String statusMessage = "";

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "com.validate.ValidateStatusBean{" +
                "isValid=" + isValid +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
