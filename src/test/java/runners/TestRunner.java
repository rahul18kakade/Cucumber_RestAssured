package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(
        features = "C:\\Users\\User\\Cucumber_RestAssured\\src\\test\\java\\functionalTest\\End2End.feature",
        glue="stepDefinitions"

                    )

public class TestRunner {
}
