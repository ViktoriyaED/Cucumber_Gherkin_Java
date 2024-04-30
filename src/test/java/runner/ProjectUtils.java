package runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProjectUtils {

    private static final Logger log = LogManager.getLogger(ProjectUtils.class);
    private static final String ENV_CHROME_OPTIONS = "CHROME_OPTIONS";
    private static final String ENV_WEB_OPTIONS = "WEB_OPTIONS";
    private static final String PROP_CHROME_OPTIONS = ENV_CHROME_OPTIONS.toLowerCase();

    private static Properties properties;
    public static String DEMO_QA_BASE_URL;
    public static String BROWSER;

    private static void initProperties() {
        if (properties == null) {
            properties = new Properties();
            if (isServerRun()) {
                properties.setProperty(PROP_CHROME_OPTIONS, System.getenv(ENV_CHROME_OPTIONS));

                if (System.getenv(ENV_WEB_OPTIONS) != null) {
                    for (String option : System.getenv(ENV_WEB_OPTIONS).split(";")) {
                        String[] webOptionArr = option.split("=");
                        properties.setProperty(webOptionArr[0], webOptionArr[1]);
                    }
                }
            } else {
                try {
                    FileInputStream fileInputStream = new FileInputStream("./src/test/resources/config.properties");
                    properties.load(fileInputStream);
                    log.info("Loading the properties file");

                } catch (IOException e) {
                    System.out.println("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
                    System.out.println("You need to create it from config.properties.TEMPLATE file.");
                    System.exit(1);
                }
            }
        }
    }

    static {
        initProperties();
        DEMO_QA_BASE_URL = properties.getProperty("demoQaBaseUrl").trim();
        BROWSER = properties.getProperty("browser").trim();
    }

    static boolean isServerRun() {
        return System.getenv("CI_RUN") != null;
    }

    static void takeScreenshot(WebDriver driver, String methodName, String className) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File(String.format("screenshots/%s.%s.png", className, methodName)));
        } catch (IOException e) {
            log.error("Failed to copy screenshot " + e.getMessage());
        }
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}
