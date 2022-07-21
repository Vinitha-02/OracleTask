package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import com.ListLogic;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListEmployee extends HttpServlet {
	private static final Logger log = LogManager.getLogger(ListEmployee.class);

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		res.setContentType("application/json");
		PrintWriter out = res.getWriter();
		BufferedReader reader = null;
		ServletContext context = getServletContext();
		Connection con = (Connection) context.getAttribute("mycon");
		log.info(con);
		try {
			reader = req.getReader();
			JSONObject jo = RequestController.Controller(reader);
			// this method converting the request into JSON format from BufferReader and
			// return the JSON value
			int employeeCode = Integer.parseInt(jo.get("employeeCode").toString());
			out.println(ListLogic.List(employeeCode, con));
			log.info("Displayed User Data");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e);
		} finally {
			reader.close();
		}
	}
}