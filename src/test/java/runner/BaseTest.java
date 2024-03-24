package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    private WebDriver driver;
    private final Map<Integer, WebDriverWait> waitMap = new HashMap<>();


    @BeforeMethod
    protected void beforeMethod() {
        // start the driver


//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--no-sandbox");
//        chromeOptions.addArguments("--disable-dev-shm-usage");
//        chromeOptions.addArguments("--headless");
//        chromeOptions.addArguments("--remote-allow-origin=*");
//        chromeOptions.addArguments("--window-size=1920,1080");
//        driver = new ChromeDriver(chromeOptions);
//        driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
    }

    @AfterMethod
    protected void afterMethod() {
        // close the driver

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
