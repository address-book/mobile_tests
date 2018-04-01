package test.tests;

import org.yaml.snakeyaml.Yaml;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Authentication {

    private AndroidDriver driver;
    private WebDriverWait wait;

    private static DesiredCapabilities createCapabilities(String value) throws FileNotFoundException {
        FileReader file = new FileReader("src/test/config/platforms.yml");
        Map<String, Object> platforms = new Yaml().load(file);

        Map<String, Object> platform = (Map<String, Object>) platforms.get(value);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform.get("platformName"));
        capabilities.setCapability("platformVersion", platform.get("platformVersion"));
        capabilities.setCapability("deviceName", platform.get("deviceName"));
        capabilities.setCapability("browserName", platform.get("browserName"));

        return capabilities;
    }

    @BeforeMethod
    public void setup() throws MalformedURLException, FileNotFoundException {

        String platformProperty = System.getProperty("PLATFORM");
        String platform = (platformProperty != null) ? platformProperty : "androidChrome";

        DesiredCapabilities capabilities = createCapabilities(platform);

        driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);
        SignInPage signInPage = homePage.navigateToSignIn();

        UserData userData = UserData.validUser();
        HomePage homePage1 = signInPage.signIn(userData);

        assertTrue(homePage1.isSignedIn("user@example.com"));
    }

    @Test
    public void signInBlankPasswordUnsuccessful() {
        driver.get("http://a.testaddressbook.com/sign_in");

        // Add Action Code

        WebElement emailElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.id("session_email")));

        UserData userData = UserData.blankPassword();

        emailElement.sendKeys(userData.getEmail());
        driver.findElement(By.id("session_password")).sendKeys(userData.getPassword());
        driver.findElement(By.name("commit")).click();

        // Add Assertion Code

        By alertNotice = By.className("alert-notice");
        assertEquals(1, driver.findElements(alertNotice).size());

        By currentUser = By.cssSelector("span[data-test=current-user]");
        assertEquals(0, driver.findElements(currentUser).size());
    }

    @Test
    public void signUpSuccessfully() {
        driver.get("http://a.testaddressbook.com/sign_up");

        // Add Action Code

        UserData userData = UserData.randomUser();

        driver.findElement(By.id("user_email")).sendKeys(userData.getEmail());
        driver.findElement(By.id("user_password")).sendKeys(userData.getPassword());
        driver.findElement(By.name("commit")).click();

        // Add Assertion Code

        assertEquals("Address Book", driver.getTitle());
    }

}