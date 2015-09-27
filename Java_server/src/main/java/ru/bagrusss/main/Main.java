package ru.bagrusss.main;

import ru.bagrusss.administration.AdminPageServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.jetbrains.annotations.NotNull;
import ru.bagrusss.frontend.SignInServlet;
import ru.bagrusss.frontend.SignUpServlet;
import ru.bagrusss.servces.AccountService;

public class Main {

    private static final String START_MESSAGE = "Run server at port ";
    public static final String RESOURSE_DIR = "static";
    public static final String ERROR_MESSAGE = "Use port as the first argument";

    public static void main(@NotNull String[] args) throws Exception {
        if (args.length != 1) {
            System.out.append(ERROR_MESSAGE);
            System.exit(1);
        }
        String portString = args[0];
        int port = Integer.valueOf(portString);
        System.out.println((new StringBuilder(START_MESSAGE)).append(portString).append('\n'));

        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AdminPageServlet()), AdminPageServlet.URL);

        context.addServlet(new ServletHolder(new SignInServlet(AccountService.getInstance())), SignInServlet.URL);
        context.addServlet(new ServletHolder(new SignUpServlet(AccountService.getInstance())), SignUpServlet.URL);

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase(RESOURSE_DIR);

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
