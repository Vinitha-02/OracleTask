package task;

import java.io.BufferedReader;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class RequestController {
	private static final Logger log = LogManager.getLogger(RequestController.class);

	public static JSONObject Controller(BufferedReader reader) {
		JSONObject jo = new JSONObject();
		try {
			StringBuffer jb = new StringBuffer();
			String line = null;
			String str;
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
			str = jb.toString();
			JSONParser jp = new JSONParser();
			jo = (JSONObject) jp.parse(str);
			log.info("process JSONRequest");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jo;
	}
}
