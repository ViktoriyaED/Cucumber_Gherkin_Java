package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"cucumber"},
        plugin = {"pretty"}
//                "json:target/cucumber-report/cucumber.json",
//                "html:target/cucumber-report/cucumber.html"},
//        monochrome = true
//        tags = "@smoke"
)

public class CucumberTest extends AbstractTestNGCucumberTests {

}
