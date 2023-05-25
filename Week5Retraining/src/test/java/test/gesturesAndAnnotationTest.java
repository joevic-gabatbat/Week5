package test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.MobileCapabilityType;

public class gesturesAndAnnotationTest {

	static IOSDriver driver;
	ExtentTest test;
	static ExtentReports report;

	@BeforeSuite
	public void beforeSuite() {
		test = CommonFunctions.generateExtentReports("iOS iPhone");
	}

	@AfterSuite
	public void closeExtentReports() {
		CommonFunctions.closeExtentReports();
	}

	@BeforeClass
	public void beforeClass() {
		XCUITestOptions cap = new XCUITestOptions();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 14 Pro");
		cap.setCapability(MobileCapabilityType.APP,
				"/Users/collabera/eclipse-workspace/Week5Retraining/src/test/resources/resource/UIKitCatalog.app");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "16.4");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		cap.setUdid("86866BA0-4C37-4DEA-982E-B431C296597A");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3600);
		try {
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
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

	@Test(priority = 1)
	public void dragFromToForDurationDemo() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Sliders")).click();
			WebElement element = driver.findElement(By.xpath("//XCUIElementTypeTable"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Map<String, Object> params = new HashMap<>();
			params.put("duration", 1.0);
			params.put("fromX", 173);
			params.put("fromY", 176);
			params.put("toX", 24);
			params.put("toY", 177);
			params.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: dragFromToForDuration", params);
			String defaultSlider = driver.findElements(By.xpath("//XCUIElementTypeSlider")).get(0)
					.getAttribute("value");
			Assert.assertEquals(defaultSlider, "0%");
			generateScreenShots("Default Slider value: " + defaultSlider, "PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void swipeDemo() {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			WebElement element = driver.findElement(AppiumBy.accessibilityId("Stack Views"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Map<String, Object> params = new HashMap<>();
			params.put("direction", "down");
			params.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: swipe", params);
			generateScreenShots("Swipe to Stack Views", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void scrollDemo() {
		try {
			driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeButton[`label=='UIKitCatalog'`]")).click();
			WebElement element = driver.findElement(AppiumBy.accessibilityId("Web View"));
			Map<String, Object> params = new HashMap<>();
			params.put("direction", "down");
			params.put("element", ((RemoteWebElement) element).getId());
			driver.executeScript("mobile:scroll", params);
			generateScreenShots("Scroll to Web View", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 4)
	public void doubleTapDemo() {
		try {
			driver.findElement(AppiumBy.accessibilityId("Stack Views")).click();
			WebElement element = driver.findElements(By.xpath("//XCUIElementTypeButton")).get(1);
			Map<String, Object> params = new HashMap<>();
			params.put("element", ((RemoteWebElement) element).getId());
			driver.executeScript("mobile:doubleTap", params);
			generateScreenShots("Double tap in Stack Views", "PASS");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
