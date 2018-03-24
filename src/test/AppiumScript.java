package test;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumScript {

    @Test
    public void openBrowser() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1");
        capabilities.setCapability("deviceName", "Android_Emulator");
        capabilities.setCapability("browserName", "Chrome");

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        driver.quit();
    }

}