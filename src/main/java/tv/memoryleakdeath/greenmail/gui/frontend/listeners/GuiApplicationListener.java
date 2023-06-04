package tv.memoryleakdeath.greenmail.gui.frontend.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import tv.memoryleakdeath.greenmail.gui.backend.GreenmailServerService;

public class GuiApplicationListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(GuiApplicationListener.class);

    private ApplicationContext ctx;

    public GuiApplicationListener(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        GreenmailServerService service = (GreenmailServerService) ctx.getBean("GreenmailServerService");
        if (service != null) {
            logger.info("Launching greenmail...");
            service.startDefaultSmtpServer();
        } else {
            logger.error("Unable to find greenmail service!");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        GreenmailServerService service = (GreenmailServerService) ctx.getBean("GreenmailServerService");
        if (service != null) {
            logger.info("Shutting down greenmail...");
            service.stopDefaultSmtpServer();
        } else {
            logger.error("Unable to find greenmail service!");
        }
    }
}
