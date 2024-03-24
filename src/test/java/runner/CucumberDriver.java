package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class CucumberDriver {

    private WebDriver driver;

    @Before
    protected void before(Scenario scenario) {
        getDriver();
    }

    @After
    protected void after(Scenario scenario) {
        driver.quit();
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
