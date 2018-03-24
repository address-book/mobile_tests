package test;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.MICROSECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class AppiumScript {

    @Test
    public void signIn() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Android_Emulator");
        capabilities.setCapability("browserName", "Chrome");

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        driver.get("http://a.testaddressbook.com");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.id("sign-in")).click();

        Wait fluentWait = new FluentWait(driver)
                .withTimeout(5, SECONDS)
                .pollingEvery(500, MICROSECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement emailElement = (WebElement) fluentWait
                .until(new Function<AndroidDriver, WebElement>() {
            public WebElement apply(AndroidDriver driver) {
                return driver.findElement(By.id("session_email"));
            }
        });

        emailElement.sendKeys("user@example.com");
        driver.findElement(By.id("session_password")).sendKeys("password");
        driver.findElement(By.name("commit")).click();

        driver.quit();
    }

}