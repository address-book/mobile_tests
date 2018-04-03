package examples.tests;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class AppiumScript {

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

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        driver.quit();
    }

}