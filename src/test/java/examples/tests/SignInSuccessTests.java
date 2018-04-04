package examples.tests;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.yaml.snakeyaml.Yaml;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SignInSuccessTests {
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
    public void signInSuccessful() throws MalformedURLException, FileNotFoundException {
        String platformProperty = System.getProperty("PLATFORM");
        String platform = (platformProperty != null) ? platformProperty : "androidChromeLocal";
        DesiredCapabilities capabilities = createCapabilities(platform);

        AndroidDriver driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), capabilities);

        HomePage homePage = new HomePage(driver);

        homePage.visit();
        homePage.getMenuButton().click();
        SignInPage signInPage = homePage.getSignInLink().click();

        signInPage.waitFor(homePage.getEmailElement);
        UserData userData = UserData.validUser();

        signInPage.getEmailElement().sendKeys(userData.getEmail());
        signInPage.getPasswordElement().sendKeys(userData.getPassword());
        HomePage homePage2 = signInPage.getSubmitButton().click();

        Boolean currentUser = homePage2.isElementPresent(homPage.getCurrentUser);

        assertTrue(currentUser);

        driver.quit();
    }

}