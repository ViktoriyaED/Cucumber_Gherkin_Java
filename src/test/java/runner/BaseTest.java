package runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public abstract class BaseTest {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    protected void beforeSuite() {
        log.info(ReportUtils.getReportHeader());
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        log.info("RUNNING " + ReportUtils.getTestClassMethodName(method));
        try {
            if (driver == null) {
                driver = DriverManager.launchBrowser();
                if (driver != null) {
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                } else {
                    log.info("Driver is null. Cannot set implicit wait.");
                }
            }
        } catch (Exception e) {
            log.fatal(Constants.EXCEPTION_ICON + "An error occurred in beforeMethod: " + e.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(Method method, ITestResult testResult) {
        ReportUtils.logTestStatistic(method, testResult);
        if (driver != null) {
            driver.quit();
             driver = null;
            log.info("Driver closed" + ReportUtils.getEndLine());
        } else {
            log.warn(Constants.WARNING_ICON + "Driver is null. No action taken.");
        }

        if (!testResult.isSuccess() && driver != null) { //&& ProjectUtils.isServerRun()) {
            ProjectUtils.takeScreenshot(driver, method.getName(), this.getClass().getName());
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
