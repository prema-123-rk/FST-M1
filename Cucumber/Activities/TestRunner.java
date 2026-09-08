package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features",
        glue = "stepDefinitions",
        plugin = {
                "pretty",
                "usage",
                "html:target/cucumber-reports/cucumber.html",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        monochrome = true
)
public class TestRunner {
}
