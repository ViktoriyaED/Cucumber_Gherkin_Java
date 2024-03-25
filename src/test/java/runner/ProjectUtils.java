package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class ProjectUtils {

    private static final String ENV_CHROME_OPTIONS = "CHROME_OPTIONS";
    private static final String ENV_WEB_OPTIONS = "WEB_OPTIONS";
    private static final String PROP_CHROME_OPTIONS = ENV_CHROME_OPTIONS.toLowerCase();

    private static Properties properties = initProperties();
    public static final String DEMO_QA_BASE_URL = properties.getProperty("demoQaBaseUrl").toLowerCase();
    static ChromeOptions chromeOptions;


    private static ChromeOptions chromeOptions() {
        chromeOptions = new ChromeOptions();
        String options = properties.getProperty(PROP_CHROME_OPTIONS);

        if (options != null) {
            for (String argument : options.split(";")) {
                chromeOptions.addArguments(argument);
            }
        }
        return chromeOptions;
    }

    private static Properties initProperties() {
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
                } catch (IOException e) {
                    System.out.println("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
                    System.out.println("You need to create it from config.properties.TEMPLATE file.");
                    System.exit(1);
                }
            }
        }

        return properties;
    }


    static boolean isServerRun() {
        return System.getenv("CI_RUN") != null;
    }

    static WebDriver createDriver() {
        WebDriver driver = new ChromeDriver(chromeOptions());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }
}
