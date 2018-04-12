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

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AppiumScript {
    private AppiumDriver driver;

    @Test
    public void signInAndroid() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Nexus");

        File relative = new File("lib/AddressBook.apk");
        capabilities.setCapability("app", relative.getCanonicalPath());
        capabilities.setCapability("appWaitActivity", "com.address.book.MainActivity");
        capabilities.setCapability("appiumVersion", "1.7.2");

        driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // <android.widget.Button content-desc="Toggle navigation">
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("android.widget.Button"))).click();

        // <android.view.View resource-id="sign-in">
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("sign-in")));

        // <android.view.View content-desc="Sign in">
        driver.findElement(MobileBy.AccessibilityId("Sign in")).click();

        // <android.widget.EditText resource-id="session_email">
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("session_email"))).sendKeys(("user@example.com"));

        // <android.widget.EditText resource-id="session_password">
        driver.findElement(By.id("session_password")).sendKeys("password");

        // <android.widget.Button content-desc="Sign in">
        driver.findElement(By.xpath("//android.widget.Button[@content-desc='Sign in']")).click();
    }

    @Test
    public void signInIOS() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "11.2");
        capabilities.setCapability("deviceName", "iPhone X");
        File relative = new File("lib/AddressBook.app.zip");
        capabilities.setCapability("app", relative.getCanonicalPath());
        capabilities.setCapability("appiumVersion", "1.7.2");

        driver = new IOSDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // <XCUIElementTypeButton name="Toggle navigation">
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("XCUIElementTypeButton"))).click();

        // <XCUIElementTypeStaticText name="Sign in">
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("Sign in"))).click();

        // <XCUIElementTypeTextField>
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("XCUIElementTypeTextField"))).sendKeys(("user@example.com"));

        // <XCUIElementTypeSecureTextField>
        driver.findElement(By.className("XCUIElementTypeSecureTextField")).sendKeys("password");

        // <XCUIElementTypeButton name="Sign in">
        driver.findElement(MobileBy.AccessibilityId("Sign in"));

        // <XCUIElementTypeButton name="Sign in">
        String selector = "type == 'XCUIElementTypeButton' AND name == 'Sign in'";
        driver.findElement(MobileBy.iOSNsPredicateString(selector));

        // <XCUIElementTypeButton name="Sign in">
        String selectorChain = "**/XCUIElementTypeOther[`name BEGINSWITH 'Address Book'`]/XCUIElementTypeButton";
        driver.findElement(MobileBy.iOSClassChain(selectorChain)).click();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";
        System.out.println(status);
        driver.quit();
    }

}