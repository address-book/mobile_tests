package examples.pages;

import io.appium.java_client.AppiumDriver;
import java.util.Objects;

public class Pages {
    private AppiumDriver driver;

    private Boolean isNative(AppiumDriver driver) {
        return driver.getContext().equals("NATIVE_APP");
    }

    private Boolean isAndroid() {
        return Objects.equals(driver.getPlatformName(), "ANDROID");
    }

    public Pages(AppiumDriver driver){
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (isAndroid() && isNative(driver)) {
            return new HomeAndroidNativePage(driver);
        } else if (!isAndroid()) {
            return new HomeIOSPage(driver);
        } else {
            return new HomePage(driver);
        }
    }

}