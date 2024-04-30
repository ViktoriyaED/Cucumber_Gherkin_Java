package cucumber;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import runner.CucumberDriver;

import static runner.ProjectUtils.DEMO_QA_BASE_URL;

public class MainPageSteps {

    private static final WebDriver driver = CucumberDriver.getDriver();

    @When("User opens main page")
    public void openMainPage() {
        driver.get(DEMO_QA_BASE_URL);
    }

    @Then("Main Page is opened")
    public void assertMainPageOpened() {
        Assert.assertEquals(driver.getTitle(), "DEMOQA");
    }

    @When("User clicks on the side menu Elements Page")
    public void userClicksOnTheSideMenuElementsPage() {
        WebElement elementsSideMenu = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsSideMenu.click();
    }

    @Then("Elements Page is opened")
    public void assertElementsPageIsOpened() {
        Assert.assertEquals(driver.getCurrentUrl(), DEMO_QA_BASE_URL + "/elements");
    }
}

