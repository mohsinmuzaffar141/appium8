package Pages;

import com.sun.javafx.scene.traversal.Direction;
import io.appium.java_client.*;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import Utilities.Capabilities;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeoutException;


public class Utility {
	public AndroidDriver driver;

	public Utility() {
		this.driver = (AndroidDriver) Capabilities.getDriverInstance();
//		PageFactory.initElements(new AppiumFieldDecorator(this.driver, Duration.ofSeconds(10)), this);
	}

	public void waitForElement(WebDriver d,WebElement element) throws Exception{
		WebDriverWait wait = new WebDriverWait(d,Duration.ofSeconds(20) );
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void fluentWaitForElement(WebDriver d,WebElement element) {
		Wait wait = new FluentWait(d)
				.withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofSeconds(3))
				.ignoring(Exception.class)
				.ignoring(TimeoutException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementEnabled(WebDriver d,WebElement element){
		WebDriverWait wait = new WebDriverWait(d, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void clickButton(WebElement buttonLocator) {
		waitForElementEnabled(driver, buttonLocator);
		buttonLocator.click();
	}

	public void setText(WebElement locator, String value) throws Exception {
		waitForElement(driver,locator);
		locator.clear();
		locator.click();
		locator.sendKeys(value);
	}

	public String getTextFunc(WebElement locator) {
		fluentWaitForElement(driver,locator);
		return locator.getText();
	}

	public void clickTextView(String textView) {
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'"+textView+"')]")).click();;
	}

	public enum DIRECTION {
		DOWN, UP, LEFT, RIGHT;
	}

	public void swipe(AndroidDriver driver, String direction) {
		Dimension size = driver.manage().window().getSize();

		int startX = 0;
		int endX = 0;
		int startY = 0;
		int endY = 0;

		switch (direction) {
			case "RIGHT":
				startY = (int) (size.height / 2);
				startX = (int) (size.width * 0.90);
				endX = (int) (size.width * 0.05);
				new TouchAction(driver)
						.longPress(PointOption.point(startX, startY))
						.moveTo(PointOption.point(endX, startY))
						.release()
						.perform();
				break;

			case "LEFT":
				startY = (int) (size.height / 2);
				startX = (int) (size.width * 0.05);
				endX = (int) (size.width * 0.90);
				new TouchAction(driver)
						.longPress(PointOption.point(startX, startY))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1)))
						.moveTo(PointOption.point(endX, startY))
						.release()
						.perform();
				break;

			case "UP":
				endY = (int) (size.height * 0.70);
				startY = (int) (size.height * 0.30);
				startX = (size.width / 2);
				new TouchAction(driver)
						.longPress(PointOption.point(startX, startY))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1)))
						.moveTo(PointOption.point(endX, endY))
						.release()
						.perform();
				break;

			case "DOWN":
				startY = (int) (size.height * 0.50);
				endY = (int) (size.height * 0.30);
				startX = (size.width / 2);
				new TouchAction(driver)
						.longPress(PointOption.point(startX, startY))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1)))
						.moveTo(PointOption.point(startX, endY))
						.release()
						.perform();
				break;

		}
	}

	public void scroll(String visibleText) throws InterruptedException {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))")).click();
	}

	public void scrollAndClick(String visibleText) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))")).click();
	}
//	public void scrollToVisibleText(String visibleText) throws InterruptedException {
//		Thread.sleep(3000);
//		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))").isDisplayed();
//		Thread.sleep(5000);
//	}

//	Swipe by coordinates of element
//	public void swipe(int[] from, int[] to) {
//		 {
//			new TouchAction(driver)
//					.press(PointOption.point(from[0], from[1]))
//					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(0)))
//					.moveTo(PointOption.point(to[0], to[1]))
//					.release()
//					.perform();
//			System.out.println("Swiped!!");
//		}
//	}

	public void swipeElementAndroid(WebElement el, Direction dir) {
		System.out.println("swipeElementAndroid(): dir: '" + dir + "'"); // always log your actions

		// Animation default time:
		//  - Android: 300 ms
		//  - iOS: 200 ms
		// final value depends on your app and could be greater
		final int ANIMATION_TIME = 200; // ms

		final int PRESS_TIME = 200; // ms

		int edgeBorder;
		PointOption pointOptionStart, pointOptionEnd;

		// init screen variables
		Rectangle rect = el.getRect();
		// sometimes it is needed to configure edgeBorders
		// you can also improve borders to have vertical/horizontal
		// or left/right/up/down border variables
		edgeBorder = 0;

		switch (dir) {
			case DOWN: // from up to down
				pointOptionStart = PointOption.point(rect.x + rect.width / 2,
						rect.y + edgeBorder);
				pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
						rect.y + rect.height - edgeBorder);
				break;
			case UP: // from down to up
				pointOptionStart = PointOption.point(rect.x + rect.width / 2,
						rect.y + rect.height - edgeBorder);
				pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
						rect.y + edgeBorder);
				break;
			case LEFT: // from right to left
				pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
						rect.y + rect.height / 2);
				pointOptionEnd = PointOption.point(rect.x + edgeBorder,
						rect.y + rect.height / 2);
				break;
			case RIGHT: // from left to right
				pointOptionStart = PointOption.point(rect.x + edgeBorder,
						rect.y + rect.height / 2);
				pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
						rect.y + rect.height / 2);
				break;
			default:
				throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
		}

		// execute swipe using TouchAction
		try {
			new TouchAction(driver)
					.press(pointOptionStart)
					// a bit more reliable when we add small wait
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
					.moveTo(pointOptionEnd)
					.release().perform();
		} catch (Exception e) {
			System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
			return;
		}

		// always allow swipe action to complete
		try {
			Thread.sleep(ANIMATION_TIME);
		} catch (InterruptedException e) {
			// ignore
		}
	}

}
