package tv.memoryleakdeath.greenmail.gui.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.icegreen.greenmail.Managers;
import com.icegreen.greenmail.smtp.SmtpServer;
import com.icegreen.greenmail.util.ServerSetup;

@Service("GreenmailServerService")
public class GreenmailServerService {
    private static final Logger logger = LoggerFactory.getLogger(GreenmailServerService.class);
    private static final int DEFAULT_SMTP_PORT = 10025;
    private static final String DEFAULT_HOST = "localhost";

    private SmtpServer smtpServer;

    public void startDefaultSmtpServer() {
        ServerSetup serverSetup = new ServerSetup(DEFAULT_SMTP_PORT, DEFAULT_HOST, ServerSetup.PROTOCOL_SMTP);
        smtpServer = new SmtpServer(serverSetup, new Managers());
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
}
