package examples.tests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;


public class BaseTest {
    AppiumDriver driver;

    private Boolean useSauce = true; //System.getProperty("USE_SAUCE") != null;

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

    @BeforeMethod
    public void setup(Method method) throws MalformedURLException, FileNotFoundException {
        String url;
        DesiredCapabilities capabilities;
        String platformProperty = System.getProperty("PLATFORM");

        if (useSauce) {
            String platform = (platformProperty != null) ? platformProperty : "iOSAppSauce";
            capabilities = createCapabilities(platform);

            String USER = System.getenv("SAUCE_USERNAME");
            String KEY = System.getenv("SAUCE_ACCESS_KEY");

            capabilities.setCapability("name", method.getName());
            String buildEnv = System.getenv("BUILD_TAG");
            if (buildEnv != null) {
                capabilities.setCapability("build", buildEnv);
            }

            url = "https://" + USER + ":" + KEY + "@ondemand.saucelabs.com/wd/hub";
        } else {
            String platform = (platformProperty != null) ? platformProperty : "iOSAppLocal";
            capabilities = createCapabilities(platform);
            url = "http://localhost:4723/wd/hub";
        }

        driver = new AppiumDriver<>(new URL(url), capabilities);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        String status = result.isSuccess() ? "passed" : "failed";

        if (useSauce) {
            JavascriptExecutor js = driver;
            js.executeScript("sauce:job-result=" + status);
        } else {
            System.out.println(status);
        }

        driver.quit();
    }

}