package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import com.DeleteLogic;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteEmployee extends HttpServlet {
	private static final Logger log = LogManager.getLogger(DeleteEmployee.class);

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		ServletContext context = getServletContext();
		Connection con = (Connection) context.getAttribute("mycon");
		log.info(con);
		BufferedReader reader = null;
		try {
			reader = req.getReader();
			JSONObject jo = RequestController.Controller(reader);
			int employeeCode = Integer.parseInt(jo.get("employeeCode").toString());
			out.println(DeleteLogic.delete(employeeCode, con));
			log.info("user deleted");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e);
		} finally {
			reader.close();
		}
	}
}
