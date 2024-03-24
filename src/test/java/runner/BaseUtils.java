package runner;

import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseUtils {

//    private static final ChromeOptions chromeOptions;
//    private static Properties properties;
//
//    private static void initProperties() {
//        if (properties == null) {
//            properties = new Properties();
//            if (isServerRun()) {
//                properties.setProperty(PROP_CHROME_OPTIONS, System.getenv(ENV_CHROME_OPTIONS));
//            } else {
//                try {
//                    InputStream inputStream = BaseUtils.class.getClassLoader().getResourceAsStream("local.properties");
//                    if (inputStream == null) {
//                        System.out.println("ERROR: The \u001B[31mlocal.properties\u001B[0m file not found in src/test/resources/ directory.");
//                        System.out.println("You need to create it from local.properties.TEMPLATE file.");
//                        System.exit(1);
//                    }
//                    properties.load(inputStream);
//                } catch (IOException ignore) {
//                }
//            }
//        }
//    }
}
