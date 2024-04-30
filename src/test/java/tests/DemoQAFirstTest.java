package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectUtils;

import java.util.List;

public class DemoQAFirstTest extends BaseTest {

    @Test
    public void navigationTest() {
        final String expectedTitle = "DEMOQA";
        getDriver().get(ProjectUtils.DEMO_QA_BASE_URL);

        Assert.assertEquals(getDriver().getTitle(), expectedTitle);
    }

    @Test
    public void testElements() {
        getDriver().get(ProjectUtils.DEMO_QA_BASE_URL);

        WebElement elementsSideMenu = getDriver().findElement(By.xpath("//h5[text()='Elements']"));
        elementsSideMenu.click();

        WebElement textBox = getDriver().findElement(By.xpath("//span[text()='Text Box']/.."));
        textBox.click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("h1.text-center")).getText(), "Text Box");
    }

    @Test
    public void testTextBox() {
        final String fullName = "John Doe";
        final String email = "johndoe@example.com";
        final String currentAddress = "address";
        final String permanentAddress = "address1";

        getDriver().get(ProjectUtils.DEMO_QA_BASE_URL);
        getDriver().findElement(By.xpath("//h5[text()='Elements']")).click();
        getDriver().findElement(By.xpath("//span[text()='Text Box']/..")).click();
        getDriver().findElement(By.cssSelector("#userName")).sendKeys(fullName);
        getDriver().findElement(By.cssSelector("#userEmail")).sendKeys(email);
        getDriver().findElement(By.cssSelector("#currentAddress")).sendKeys(currentAddress);
        getDriver().findElement(By.cssSelector("#permanentAddress")).sendKeys(permanentAddress);
        WebElement submitButton = getDriver().findElement(By.cssSelector("#submit"));
        ProjectUtils.scrollToElement(getDriver(), submitButton);
        submitButton.click();

        WebElement outputBox = getDriver().findElement(By.cssSelector("#output"));
        List<WebElement> outputList = outputBox.findElements(By.tagName("p"));

        Assert.assertTrue(outputBox.isDisplayed());
        Assert.assertEquals(outputList.get(0).getText(), "Name:" + fullName);
        Assert.assertEquals(outputList.get(1).getText(), "Email:" + email);
        Assert.assertEquals(outputList.get(2).getText(), "Current Address :" + currentAddress);
        Assert.assertEquals(outputList.get(3).getText(), "Permananet Address :" + permanentAddress);
    }
}
