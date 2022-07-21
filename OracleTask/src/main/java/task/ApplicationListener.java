package task;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {
	private static final Logger log = LogManager.getLogger(ApplicationListener.class);

	public void contextInitialized(ServletContextEvent event) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "12345");
			log.info("Connection established");
			ServletContext ctx = event.getServletContext();
			ctx.setAttribute("mycon", con);

		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e);
		}
	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}
}
