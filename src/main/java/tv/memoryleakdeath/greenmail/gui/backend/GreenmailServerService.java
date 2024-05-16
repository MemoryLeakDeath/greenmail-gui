package tv.memoryleakdeath.greenmail.gui.backend;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.icegreen.greenmail.Managers;
import com.icegreen.greenmail.smtp.SmtpServer;
import com.icegreen.greenmail.store.FolderException;
import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.ServerSetup;

@Service("GreenmailServerService")
public class GreenmailServerService {
    private static final Logger logger = LoggerFactory.getLogger(GreenmailServerService.class);
    private static final int DEFAULT_SMTP_PORT = 25;
    private static final String DEFAULT_HOST = "0.0.0.0";

    private SmtpServer smtpServer;
    private Managers managers = new Managers();

    public void startDefaultSmtpServer() {
        ServerSetup serverSetup = new ServerSetup(DEFAULT_SMTP_PORT, DEFAULT_HOST, ServerSetup.PROTOCOL_SMTP);
        smtpServer = new SmtpServer(serverSetup, managers);
        logger.info("Starting smtp server with default settings -- Port: {} host: {}", DEFAULT_SMTP_PORT, DEFAULT_HOST);
        smtpServer.startService();
    }

    public boolean isServerUp() {
        if (smtpServer != null) {
            return smtpServer.isRunning();
        }
        return false;
    }

    public void stopDefaultSmtpServer() {
        if (smtpServer != null) {
            smtpServer.stopService(5000);
            logger.info("Stopped smtp server with default settings");
        }
    }

    public Collection<GreenMailUser> getUsers() {
        return managers.getUserManager().listUser();
    }

    public GreenMailUser getUser(String email) {
        return managers.getUserManager().getUserByEmail(email);
    }

    public MailFolder getUserInbox(GreenMailUser user) {
        try {
            return managers.getImapHostManager().getInbox(user);
        } catch (FolderException e) {
            logger.error("Unable to find inbox for user: {}", user.getEmail());
            return null;
        }
    }
}
