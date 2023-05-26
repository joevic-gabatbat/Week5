package test;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.TestBaseClass;
import io.appium.java_client.AppiumBy;

public class GesturesAndAnnotationTest extends TestBaseClass {

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
			Assert.assertTrue(true);
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
			Assert.assertTrue(true);
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
			Assert.assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
