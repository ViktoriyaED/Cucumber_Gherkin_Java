package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

public class DemoQAFirstTest extends BaseTest {

    @Test
    public void navigationTest() {
        final String expectedTitle = "DEMOQA";
        getDriver().get(ProjectUtils.DEMO_QA_BASE_URL);

        Assert.assertEquals(getDriver().getTitle(), expectedTitle);
    }
}
