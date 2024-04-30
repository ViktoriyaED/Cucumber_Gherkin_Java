package runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {

    private static final Logger log = LogManager.getLogger(DriverManager.class);

    protected static WebDriver launchBrowser() {
        try {
            return switch (ProjectUtils.BROWSER) {
                case "chrome" -> {
                    log.info("INFO: Launching " + ProjectUtils.BROWSER);
                    yield new ChromeDriver(chromeOptions());
                }
                case "firefox" -> {
                    log.info("INFO: Launching " + ProjectUtils.BROWSER);
                    yield new FirefoxDriver();
                }
                case "edge" -> {
                    log.info("INFO: Launching " + ProjectUtils.BROWSER);
                    yield new EdgeDriver();
                }
                case "ie" -> {
                    log.info("INFO: Launching " + ProjectUtils.BROWSER);
                    yield new InternetExplorerDriver();
                }
                default -> {
                    log.warn("WARNING: " + ProjectUtils.BROWSER + " is NOT match any options. Chrome launched.");
                    yield new ChromeDriver();
                }
            };
        } catch (Exception e) {
            log.error("Invalid browser name " + ProjectUtils.BROWSER);
            return null;
        }
    }

    private static ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--incognito");
        return chromeOptions;
    }
}
