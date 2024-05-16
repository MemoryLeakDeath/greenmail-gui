package tv.memoryleakdeath.greenmail.gui.frontend.controller.model;

import java.io.Serializable;
import java.util.List;

import com.icegreen.greenmail.store.StoredMessage;

import jakarta.mail.internet.InternetAddress;

public class MailUserDataModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;
    private int totalInboxMessages;
    private List<MailMessageModel> inboxMessages;

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

    public List<MailMessageModel> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<MailMessageModel> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    public String getFromAddress(InternetAddress address) {
        return address.getAddress();
    }
}
