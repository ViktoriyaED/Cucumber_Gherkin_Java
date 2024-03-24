package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"src/test/java/cucumber"},
        plugin = {"pretty"}
)

public class CucumberTest extends AbstractTestNGCucumberTests {

}
