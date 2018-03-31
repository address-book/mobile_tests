package test.tests;

import test.data.UserData;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
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

    @BeforeMethod
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Android_Emulator");
        capabilities.setCapability("browserName", "Chrome");

        driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test(dataProvider = "validUser", dataProviderClass = UserData.class)
    public void signIn(String email, String password) {
        driver.get("http://a.testaddressbook.com/sign_in");

        WebElement emailElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("session_email")));

        emailElement.sendKeys(email);
        driver.findElement(By.id("session_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        By currentUser = By.cssSelector("span[data-test=current-user]");
        assertEquals(1, driver.findElements(currentUser).size());
    }

    @Test
    public void signInBlankPasswordUnsuccessful() {
        driver.get("http://a.testaddressbook.com/sign_in");

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

    @Test(dataProvider = "randomUser", dataProviderClass = UserData.class)
    public void signUpSuccessfully(String email, String password) {
        driver.get("http://a.testaddressbook.com/sign_up");

        // Add Action Code

        driver.findElement(By.id("user_email")).sendKeys(email);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        // Add Assertion Code

        assertEquals("Address Book", driver.getTitle());
    }

}