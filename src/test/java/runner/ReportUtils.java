package runner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportUtils {

    public static final Logger log = LogManager.getLogger(ReportUtils.class);
    private static final String H_LINE = String.format("%s%n", "== ".repeat(45));
    private static final String END_LINE = String.format("%n%s", "—".repeat(90));

    public static String getEndLine() {
        return END_LINE;
    }

    public static String getReportHeader() {
        String reportHeader = """
                T E S T    R E P O R T
                Date: %s
                """.formatted(getReportDate());
        return "\n" + H_LINE + reportHeader + H_LINE;
    }

    public static String getReportDate() {
        LocalDateTime timeNow  = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeNow.format(formatter);
    }

    public static String getTestStatus(ITestResult testResult) {
        int status = testResult.getStatus();

        return switch (status) {
            case 1 -> "PASS";
            case 2 -> "FAIL";
            case 3 -> "SKIP";
            default -> "UNDEFINED";
        };
    }

    public static void logTestStatistic(Method method, ITestResult testResult) {
        String testStatus = getTestStatus(testResult);

        switch (testStatus) {
            case "PASS" -> log.info(Constants.SUCCESS_ICON + getTestStatistics(method, testResult));
            case "FAIL" -> log.error(Constants.ERROR_ICON + getTestStatistics(method, testResult));
            case "SKIP" -> log.warn(Constants.WARNING_ICON + ReportUtils.getTestStatistics(method, testResult));
            default -> log.warn(Constants.WARNING_ICON + "Unknown test status: " + testStatus);
        }
    }

    public static String getTestRunTime(ITestResult testResult) {
        final long time = testResult.getEndMillis() - testResult.getStartMillis();

        long minutes = Duration.ofMillis(time).toMinutes();
        long seconds = Duration.ofMillis(time).minusMinutes(minutes).getSeconds();
        long milliseconds = Duration.ofMillis(time).toMillis() % 1000;

        return String.format("%d min %d sec %d ms", minutes, seconds, milliseconds);
    }

    public static String getTestStatistics(Method method, ITestResult testResult) {
        return "\n" + getTestClassMethodName(method)
                + "   ----   " + getTestStatus(testResult) + "\t Run Time: " + getTestRunTime(testResult) + "\n";
    }

    public static String getTestClassMethodName(Method method) {
        return method.getDeclaringClass().getSimpleName() + "." + method.getName();
    }
}
