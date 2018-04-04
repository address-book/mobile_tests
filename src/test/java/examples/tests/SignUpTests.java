package examples.tests;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertEquals;

public class SignUpTests {

    @Test()
    public void signInSuccessful() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Android_Emulator");
        capabilities.setCapability("browserName", "Chrome");

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        driver.get("http://a.testaddressbook.com/sign_up");

        // Define email and password

        driver.findElement(By.id("user_email")).sendKeys(email);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.name("commit")).click();

        assertEquals("Address Book", driver.getTitle());

        driver.quit();
    }

}