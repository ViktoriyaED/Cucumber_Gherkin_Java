package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CucumberDriver {

    private static final Logger log = LoggerFactory.getLogger(CucumberDriver.class);
    private static WebDriver driver;
    private static WebDriverWait wait3;

    @Before
    protected static void before() {
        getDriver();
    }

    @After
    protected static void after(Scenario scenario) {
        if (scenario.isFailed()) {
            captureAndAttachScreenshot(scenario);
        }
        quitDriver();
    }

    private static void quitDriver() {
        if (driver != null) {
            driver.quit();
            log.info("Quit driver");
            driver = null;
            wait3 = null;
        }
    }

    private static void captureAndAttachScreenshot(Scenario scenario) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverManager.launchBrowser();
        }
        return driver;
    }

    public static WebDriverWait getWait3() {
        if (wait3 == null) {
            wait3 = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
        }
        return wait3;
    }
}
