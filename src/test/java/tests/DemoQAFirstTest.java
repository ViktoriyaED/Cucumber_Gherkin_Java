package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

public class DemoQAFirstTest extends BaseTest {

    @Test
    public void navigationTest() {
        final String expectedTitle = "DEMOQA1";
        getDriver().get("https://demoqa.com/");

        Assert.assertEquals(getDriver().getTitle(), expectedTitle);
    }
}
