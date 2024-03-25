package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    private WebDriver driver;
    private final Map<Integer, WebDriverWait> waitMap = new HashMap<>();

    @BeforeMethod
    protected void beforeMethod() {
        driver = ProjectUtils.createDriver();
    }

    @AfterMethod
    protected void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriverWait getWait(int seconds) {
        return waitMap.computeIfAbsent(seconds, duration -> new WebDriverWait(driver, Duration.ofSeconds(duration)));
    }
}
