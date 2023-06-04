package tv.memoryleakdeath.greenmail.gui.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import tv.memoryleakdeath.greenmail.gui.frontend.interceptors.CSPHeaderFilter;
import tv.memoryleakdeath.greenmail.gui.frontend.interceptors.CorsHeaderFilter;
import tv.memoryleakdeath.greenmail.gui.frontend.listeners.GuiApplicationListener;

public class GuiInit implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.scan("tv.memoryleakdeath.greenmail.gui.config");

        // Manage the lifecycle of the root application context
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.addListener(new GuiApplicationListener(rootContext));

        servletContext.addFilter("corsFilter", new CorsHeaderFilter()).addMappingForUrlPatterns(null, true, "/*");
        servletContext.addFilter("cspFilter", new CSPHeaderFilter()).addMappingForUrlPatterns(null, true, "/*");

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
