package Utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.testng.asserts.SoftAssert;

public class Capabilities extends DesiredCapabilities {
	private static AppiumDriver appiumDriver;
	private static String jsonFilePath = "src/test/resources/Capabilities/config.json";
	private static String apkFilePath = System.getProperty("user.dir");
	public static AndroidDriver Driver;
	public static SoftAssert softAssertH;


	public static void startService() {
		try {
			softAssertH = new SoftAssert();
			JsonReader jsonReader = new JsonReader(jsonFilePath);
			jsonReader = new JsonReader("src/test/resources/Capabilities/"+jsonReader.getValue("capability"));
			
			UiAutomator2Options options=new UiAutomator2Options()
					.setUdid(jsonReader.getValue("uid"))
					.setPlatformVersion(jsonReader.getValue("platformVersion"))
					.setPlatformName(jsonReader.getValue("platformName"))
					.setNoReset(false)
					.setAutoGrantPermissions(true);
			if(!apkFilePath.contains(".apk")) {
				apkFilePath = apkFilePath+jsonReader.getValue("app");
			}
			options.setApp(apkFilePath);

			appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
			appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch (Exception e) {
			System.out.println("Cause: "+e.getCause());
			System.out.println("Message: "+e.getMessage());
			e.printStackTrace();
		}
	}


	public static void stopService() {
		appiumDriver.quit();
	}

	public static AppiumDriver getDriverInstance(){
		return appiumDriver;
	}
}