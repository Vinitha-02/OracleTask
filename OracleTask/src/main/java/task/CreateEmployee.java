package task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import com.CreateLogic;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateEmployee extends HttpServlet {
	private static final Logger log = LogManager.getLogger(CreateEmployee.class);

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
			String employeeName = jo.get("employeeName").toString();
			String Designation = jo.get("Designation").toString();
			int Salary = Integer.parseInt(jo.get("Salary").toString());
			out.println(CreateLogic.insert(employeeName, Designation, Salary, con));
			log.info("Data Inserted");
		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e);
		} finally {
			reader.close();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
}