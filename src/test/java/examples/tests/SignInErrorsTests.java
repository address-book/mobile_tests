package examples.tests;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.yaml.snakeyaml.Yaml;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class SignInErrorsTests {

    private static DesiredCapabilities createCapabilities(String value) throws FileNotFoundException {
        FileReader file = new FileReader("src/test/java/examples/config/platforms.yml");
        Map<String, Object> platforms = new Yaml().load(file);
        Map<String, Object> platform = (Map<String, Object>) platforms.get(value);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String key: platform.keySet()) {
            capabilities.setCapability(key, platform.get(key));
        }
        return capabilities;
    }

    @Test
    public void signInBlankPasswordUnsuccessful() throws MalformedURLException, FileNotFoundException {
        String platformProperty = System.getProperty("PLATFORM");
        String platform = (platformProperty != null) ? platformProperty : "androidChromeLocal";
        DesiredCapabilities capabilities = createCapabilities(platform);

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        //
        // Rewrite Test Declaratively
        //

//        driver.get("http://a.testaddressbook.com");
//        driver.findElement(By.tagName("button")).click();
//        driver.findElement(By.id("sign-in")).click();
//
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//
//        WebElement emailElement = wait.until(
//                ExpectedConditions.presenceOfElementLocated(
//                        By.id("session_email")));
//
//        UserData userData = UserData.blankPassword();
//
//        emailElement.sendKeys(userData.getEmail());
//        driver.findElement(By.id("session_password")).sendKeys(userData.getPassword());
//        driver.findElement(By.name("commit")).click();
//
//        By alertNotice = By.className("alert-notice");
//        assertEquals(1, driver.findElements(alertNotice).size());
//
//        assertEquals("Address Book - Sign In", driver.getTitle());
//        assertEquals("http://a.testaddressbook.com/session", driver.getCurrentUrl());
//
//        By currentUser = By.cssSelector("span[data-test=current-user]");
//        assertEquals(0, driver.findElements(currentUser).size());

        driver.quit();
    }

}