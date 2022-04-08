package runners;

import com.cucumber.listener.Reporter;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
    @CucumberOptions(
            features = "C:\\Users\\User\\Cucumber_RestAssured\\src\\test\\java\\functionalTest\\End2End.feature",
            glue="stepDefinitions",
            plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                    "html:target/html-reports/reports.html"},
            dryRun=true,
            monochrome = true,
            publish = true

                    )

public class TestRunner {

}
