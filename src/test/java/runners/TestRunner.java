package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
    @CucumberOptions(
            features = "C:\\Users\\User\\Cucumber_RestAssured\\src\\test\\java\\functionalTest\\End2End.feature",
            glue="stepDefinitions",
            plugin = {"pretty",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                    "html:target/html-reports/reports.html"},
            dryRun = true,
            monochrome = true,
            publish = true,
            tags= "@SmokeTest or @RegressionTest"

                    )

public class TestRunner {

}
