package tv.memoryleakdeath.greenmail.gui.frontend.controller.model;

import java.io.Serializable;
import java.util.List;

public class MailUsersModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<MailUserDataModel> userData;

    public List<MailUserDataModel> getUserData() {
        return userData;
    }

    public void setUserData(List<MailUserDataModel> userData) {
        this.userData = userData;
    }
}
