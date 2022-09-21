package StepDefinitions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src/test/resources/Features",
		glue= {"StepDefinitions"},
		tags = "@internationalTransferPreReq",
		monochrome=true,
		plugin= {"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"html:target/HtmlReports.html",
				"json:target/cucumber.json",
				"junit:target/junitReports.xml",
				"rerun:target/cucumber-reports/rerun.txt"
				},
		dryRun = false
		)
public class TestRunner extends AbstractTestNGCucumberTests {


}