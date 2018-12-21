package SW1.lost_things;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

public class Controller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req.getServletPath().equals("/") && req.getParameter("api").equals("true")) {
			resp.setHeader("Content-Type", "application/json");
			if(req.getParameter("action").equals("show_tables")) {
				resp.setStatus(HttpStatus.OK_200);
				Model m = new Model();
				resp.getWriter().print("[{\"raw_tables\":\""+m.table_info()+"\"}]");
			}
		}
		else if(req.getServletPath().equals("/")) {
			try {
				File f = new File(this.getClass().getResource( "/www/index.html" ).toURI());
				if(f.exists() && !f.isDirectory()) {
					resp.setStatus(HttpStatus.OK_200);
					byte[] raw_page = Files.readAllBytes(f.toPath());
					String page = new String(raw_page, StandardCharsets.UTF_8);
					resp.getWriter().print(page);
				}
				else {
					resp.setStatus(HttpStatus.OK_200);
					resp.getWriter().println("Server is just created, main page not found!");
				}
			} catch (Exception e) {
				resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				resp.getWriter().println("Server Error!");
			}
		}
		else {
			try {
				File f = new File(this.getClass().getResource( "/www" + req.getServletPath() ).toURI());
				if(f.exists() && !f.isDirectory()) {
					resp.setStatus(HttpStatus.OK_200);
					byte[] raw_page = Files.readAllBytes(f.toPath());
					String page = new String(raw_page, StandardCharsets.UTF_8);
					resp.getWriter().print(page);
				}
				else {
					resp.setStatus(HttpStatus.NOT_FOUND_404);
					resp.getWriter().println("File not found!");
				}
			} catch (Exception e) {
				resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
				resp.getWriter().println("Server Error!");
			}
		}
	}
}
