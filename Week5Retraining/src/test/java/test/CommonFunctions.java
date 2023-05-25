package test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;

public class CommonFunctions {
	
	static ExtentTest test;
	static ExtentReports report;
	
	static String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	
	public static ExtentTest generateExtentReports(String executionDevice) {
		File file = new File(executionDevice + " - " + dateName);
		file.mkdir();
		report = new ExtentReports(executionDevice + " - " + dateName + "/ExecutionReport.html");
		test = report.startTest("TestNG Annotation and Gestures : " + executionDevice);
		return test;
	}
	
	public static void closeExtentReports() {
		report.endTest(test);
		report.flush();
	}
	
}
