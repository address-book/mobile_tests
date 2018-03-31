package test.tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class Authentication {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Android_Emulator");
        capabilities.setCapability("browserName", "Chrome");

        driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        wait = new WebDriverWait(driver, 10);
        driver.get("http://a.testaddressbook.com/sign_in");
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void signInSuccessful() {
        WebElement emailElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("session_email")));

        emailElement.sendKeys("user@example.com");
        driver.findElement(By.id("session_password")).sendKeys("password");
        driver.findElement(By.name("commit")).click();

        By currentUser = By.cssSelector("span[data-test=current-user]");
        assertEquals(1, driver.findElements(currentUser).size());
    }

    @Test
    public void signInBlankPasswordUnsuccessful() {
        // Add Action Code

        WebElement emailElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("session_email")));

        emailElement.sendKeys("user@example.com");
        driver.findElement(By.id("session_password")).sendKeys("");
        driver.findElement(By.name("commit")).click();

        // Add Assertion Code

        By alertNotice = By.className("alert-notice");
        assertEquals(1, driver.findElements(alertNotice).size());

        By currentUser = By.cssSelector("span[data-test=current-user]");
        assertEquals(0, driver.findElements(currentUser).size());
    }

}