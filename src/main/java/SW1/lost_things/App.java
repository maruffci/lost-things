package SW1.lost_things;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.servlet.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Server server = new Server(3210);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(Controller.class, "/");
        try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
