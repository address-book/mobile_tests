package examples.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class AppiumScript {
    private AppiumDriver driver;

    private static DesiredCapabilities createCapabilities(String value) throws FileNotFoundException {
        FileReader file = new FileReader("src/test/java/examples/config/platforms.yml");
        Map<String, Object> platforms = (Map<String, Object>) new Yaml().load(file);
        Map<String, Object> platform = (Map<String, Object>) platforms.get(value);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (String key : platform.keySet()) {
            capabilities.setCapability(key, platform.get(key));
        }
        return capabilities;
    }

    @Test
    public void signInAndroid() throws IOException {
        DesiredCapabilities capabilities = createCapabilities("androidAppLocal");
        driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("android.widget.Button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("sign-in"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("session_email"))).sendKeys(("user@example.com"));
        driver.findElement(By.id("session_password")).sendKeys("password");
        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Sign in']")).click();
    }

    @Test
    public void signInAndroidTO() throws IOException {
        DesiredCapabilities capabilities = createCapabilities("androidAppTO");
        driver = new AndroidDriver<>(
                new URL("https://eu1.appium.testobject.com/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 90);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("android.widget.Button"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("sign-in"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("session_email"))).sendKeys(("user@example.com"));
        driver.findElement(By.id("session_password")).sendKeys("password");
        driver.findElement(By.xpath("//android.widget.Button[@text='Sign in']")).click();
    }

    @Test
    public void signInIOSTO() throws IOException {
        DesiredCapabilities capabilities = createCapabilities("iOSAppTO");
        driver = new IOSDriver<>(
                new URL("https://eu1.appium.testobject.com/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("XCUIElementTypeButton"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("Sign in"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("XCUIElementTypeTextField"))).sendKeys(("user@example.com"));
        driver.findElement(By.className("XCUIElementTypeSecureTextField")).sendKeys("password");
        String selector = "type == 'XCUIElementTypeButton' AND name == 'Sign in'";
        driver.findElement(MobileBy.iOSNsPredicateString(selector)).click();
    }

    @Test
    public void signInIOS() throws IOException {
        DesiredCapabilities capabilities = createCapabilities("iOSAppLocal");
        driver = new IOSDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("XCUIElementTypeButton"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("Sign in"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("XCUIElementTypeTextField"))).sendKeys(("user@example.com"));
        driver.findElement(By.className("XCUIElementTypeSecureTextField")).sendKeys("password");
        String selector = "type == 'XCUIElementTypeButton' AND name == 'Sign in'";
        driver.findElement(MobileBy.iOSNsPredicateString(selector)).click();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        System.out.println(status);
        driver.quit();
    }

}