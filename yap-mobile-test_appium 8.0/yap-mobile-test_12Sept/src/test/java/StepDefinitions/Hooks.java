package StepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Utilities.Capabilities;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks{


    @Before
    public void setUp() throws Exception {
        Capabilities.startService();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if(scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) Capabilities.getDriverInstance()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "image");
                System.out.println("Screenshot Completed");
            }}catch (Exception e) {
            System.out.println(e);
        }
        Capabilities.stopService();
//		softAssertH.assertAll();
    }
}
