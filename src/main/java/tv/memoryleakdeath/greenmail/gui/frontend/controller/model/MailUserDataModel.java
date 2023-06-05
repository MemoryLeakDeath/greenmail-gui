package tv.memoryleakdeath.greenmail.gui.frontend.controller.model;

import java.io.Serializable;
import java.util.List;

import com.icegreen.greenmail.store.StoredMessage;

public class MailUserDataModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private int totalInboxMessages;
    private List<StoredMessage> inboxMessages;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalInboxMessages() {
        return totalInboxMessages;
    }

    public void setTotalInboxMessages(int totalInboxMessages) {
        this.totalInboxMessages = totalInboxMessages;
    }

    public List<StoredMessage> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<StoredMessage> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }
}
