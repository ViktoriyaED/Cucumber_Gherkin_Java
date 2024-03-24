package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"cucumber"},
        plugin = {"pretty"}
)

public class CucumberTest extends AbstractTestNGCucumberTests {

}
