package net.instantcom.mm7;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * <p>
 * starting up Jetty with {@link MM7Servlet} wired up with Spring
 * </p>
 * Created by bardug on 6/26/2016.
 */
public class MM7ServletInJettyWithSpring {
    private static final String CONTEXT_PATH = "/";
    private static final String CONFIG_LOCATION_PACKAGE = "net.instantcom.mm7";
    private static final String MAPPING_URL = "/";

    private Server webServer;

    public static void main(String[] args) throws Exception {
        new MM7ServletInJettyWithSpring().start();
    }

    private void start() throws Exception {
        initJetty();
        addServletContext();
        webServer.start();
        webServer.join();
    }

    private void initJetty() {

//        ServerConnector httpConnector = new ServerConnector(webServer); // java 8
        InetSocketAddress addr = new InetSocketAddress("127.0.0.1", 8001);
        webServer = new Server(addr);
    }

    private void addServletContext() throws IOException {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(CONTEXT_PATH);
        context.addEventListener(new ContextLoaderListener());
        // MM7 servlet
        ServletHolder mm7Servlet = new ServletHolder(MM7Servlet.class);
        context.setAttribute(MM7Servlet.VASP_BEAN_ATTRIBUTE, "mm7Vasp"); // bean id as configured in spring xml context
        context.addServlet(mm7Servlet, "/mm7");
    }
}
