package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "/Users/parth/Documents/Project/SA_Tendable/src/main/java/feature",
        glue = {"stepDefination"},
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner {
}
