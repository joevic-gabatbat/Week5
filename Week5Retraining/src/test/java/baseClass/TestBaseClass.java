package baseClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import functions.CommonFunctions;
import functions.ReadCapabilities;
import functions.ReadConfigFile;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestBaseClass {
	
	public static IOSDriver driver;
	public ExtentTest test;
	static ExtentReports report;
	ReadConfigFile rcf = new ReadConfigFile();
	String capsFilePath = rcf.getSpecificUrlProperties("iOSPhoneJSONPath");
	ReadCapabilities rc = new ReadCapabilities();
	String iOSAppPath = rcf.getSpecificUrlProperties("iOSAppPath");

	@BeforeSuite
	public void beforeSuite() {
		test = CommonFunctions.generateExtentReports("iOS iPhone");
	}

	@AfterSuite
	public void closeExtentReports() {
		CommonFunctions.closeExtentReports();
	}

	@BeforeClass
	public void beforeClass() throws FileNotFoundException, IOException, ParseException {
		XCUITestOptions cap = new XCUITestOptions();
		File fs = new File(iOSAppPath);
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, rc.getDeviceName(capsFilePath));
		cap.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, rc.getPlatformVersion(capsFilePath));
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, rc.getAutomationName(capsFilePath));
		cap.setUdid(rc.getUDID(capsFilePath));
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);
		try {
			driver = new IOSDriver(new URL(rcf.getSpecificUrlProperties("url")), cap);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void closeTest() {
//		driver.close();
		driver.quit();
	}

	@BeforeTest
	public void beforeTest() {
		test.log(LogStatus.INFO, "*****Before Test*****");
	}

	@AfterTest
	public void afterTest() {
		test.log(LogStatus.INFO, "*****After Test*****");
	}

	@BeforeMethod
	public void beforeMethod() {
		test.log(LogStatus.INFO, "*****Before Method*****");
	}

	@AfterTest
	public void afterMethod() {
		test.log(LogStatus.INFO, "*****After Method*****");
	}

	public void generateScreenShots(String info, String status) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/iOSTestScreenshots/" + "ScreenShots" + dateName
				+ ".png";
		if (status == "PASS") {
			test.log(LogStatus.PASS, info + test.addScreenCapture(destination));
		} else {
			test.log(LogStatus.FAIL, info + test.addScreenCapture(destination));
		}
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
	}
}
