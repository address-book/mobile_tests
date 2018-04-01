package test.tests;

import test.data.UserData;
import test.pages.HomePage;

import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class Authentication {

    private AndroidDriver driver;

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

        assertTrue(homePage1.isSignedIn(userData.getEmail()));
    }

    @Test
    public void signInBlankPasswordUnsuccessful() {
        //
        // Rewrite Test Declaratively
        //

        HomePage homePage = HomePage.visit(driver);
        SignInPage signInPage = homePage.navigateToSignIn();

        UserData userData = UserData.blankPassword();
        HomePage homePage1 = signInPage.signIn(userData);

        assertTrue(homePage1.hasAlertNotice());
        assertFalse(homePage1.isSignedIn(userData.getEmail()));
    }

    @Test
    public void signUpSuccessfully() {
        //
        // Rewrite Test Declaratively
        //

        HomePage homePage = HomePage.visit(driver);
        SignInPage signInPage = homePage.navigateToSignIn();
        SignUpPage signUpPage = signInPage.navigateToSignUp();

        UserData userData = UserData.randomUser();
        HomePage homePage1 = signUpPage.signUp(userData);

        assertTrue(homePage1.isSignedIn(userData.getEmail()));
    }

}