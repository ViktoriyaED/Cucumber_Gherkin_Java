package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import runner.CucumberDriver;
import runner.ProjectUtils;

import java.util.List;

public class ElementsSteps {

    private static final WebDriver driver = CucumberDriver.getDriver();
    private static final WebDriverWait wait3 = CucumberDriver.getWait3();

    @And("List of 9 Elements submenus is shown")
    public void listOfElementsItemsIsShown() {
        List<WebElement> elementsSubmenus = driver.findElements(By.xpath("//div[text()='Elements']/../..//following-sibling::div//li"));
        Assert.assertEquals(elementsSubmenus.size(), 9);
    }

    @And("List of Elements submenus contains the following elements:")
    public void listOfElementsSubmenusContainsTheFollowingElements(List<String> elements) {
        List<WebElement> elementsSubmenus = driver.findElements(By.xpath("//div[text()='Elements']/../..//following-sibling::div//li"));
        for (int i = 0; i < elementsSubmenus.size(); i++) {
            Assert.assertEquals(elementsSubmenus.get(i).getText(), elements.get(i));
        }
    }

    @When("User clicks on the Text Box side menu")
    public void userClicksOnTheTextBoxSideMenu() {
        WebElement textBox = driver.findElement(By.xpath("//span[text()='Text Box']/.."));
        textBox.click();
    }

    @And("User fill out the Full Name field with {string}")
    public void userFillOutTheFullName(String fullName) {
        driver.findElement(By.cssSelector("#userName")).sendKeys(fullName);
    }

    @And("User fill out the Email field with {string}")
    public void userFillOutTheEmail(String email) {
        driver.findElement(By.cssSelector("#userEmail")).sendKeys(email);
    }

    @And("User fill out the Current Address field with {string}")
    public void userFillOutTheCurrentAddress(String currentAddress) {
        driver.findElement(By.cssSelector("#currentAddress")).sendKeys(currentAddress);
    }

    @And("User fill out the Permanent Address field with {string}")
    public void userFillOutThePermanentAddress(String permanentAddress) {
        driver.findElement(By.cssSelector("#permanentAddress")).sendKeys(permanentAddress);
    }

    @And("User clicks on the Submit button")
    public void userClicksOnTheSubmitButton() {
        WebElement submitButton = driver.findElement(By.cssSelector("#submit"));
        ProjectUtils.scrollToElement(driver, submitButton);
        wait3.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }

    @Then("User should see a confirmation message containing {string}")
    public void userShouldSeeConfirmationMessageContaining(String expectedConfirmationMessage) {
        List <WebElement> confirmationMessageList = driver.findElements(By.cssSelector("#output p"));
            wait3.until(ExpectedConditions.visibilityOfAllElements(confirmationMessageList));
        for (WebElement element : confirmationMessageList) {
            Assert.assertTrue(element.getText().contains(expectedConfirmationMessage));
        }
    }

    @Then("Text Box header is displayed")
    public void textBoxHeaderIsDisplayed() {
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.text-center")).getText(), "Text Box");
    }

    @And("Full Name input field is displayed")
    public void fullNameInputFieldIsDisplayed() {
        driver.findElement(By.cssSelector("#userName")).isDisplayed();
    }

    @And("Email input field is displayed")
    public void emailInputFieldIsDisplayed() {
        driver.findElement(By.cssSelector("#userEmail")).isDisplayed();
    }

    @And("Current Address input field is displayed")
    public void currentAddressInputFieldIsDisplayed() {
        driver.findElement(By.cssSelector("#currentAddress")).isDisplayed();
    }

    @And("Permanent Address input field is displayed")
    public void permanentAddressInputFieldIsDisplayed() {
        driver.findElement(By.cssSelector("#permanentAddress")).isDisplayed();
    }
}
