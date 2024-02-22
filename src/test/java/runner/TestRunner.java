package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        glue = "steps",
        features = "src/test/resources/features/votes.feature",
        plugin = {"pretty", "html:target/default-cucumber-reports", "json:target/cucumber.json"}
)
public class TestRunner extends AbstractTestNGCucumberTests {

}
