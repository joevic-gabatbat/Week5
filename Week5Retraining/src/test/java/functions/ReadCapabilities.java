package functions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadCapabilities {
	
	JSONParser parser = new JSONParser();

	public String getDeviceName(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String deviceName = (String) jsonObject.get("DeviceName");
		return deviceName;
	}

	public String getPlatformVersion(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String platformVersion = (String) jsonObject.get("PlatformVersion");
		return platformVersion;
	}

	public String getAutomationName(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String automationName = (String) jsonObject.get("AutomationName");
		return automationName;
	}

	public String getUDID(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String udID = (String) jsonObject.get("UDID");
		return udID;
	}

	public String getPlatformName(String filePath) throws FileNotFoundException, IOException, ParseException {
		Object obj = parser.parse(new FileReader(filePath));
		JSONObject jsonObject = (JSONObject) obj;
		String platformName = (String) jsonObject.get("PlatformName");
		return platformName;
	}
}
