package examples.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeIOSPage extends HomePage {
    @iOSFindBy(accessibility = "Toggle navigation")
    protected WebElement menu;

    @iOSFindBy(accessibility = "Sign in")
    protected WebElement signIn;

    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name='navigation']// XCUIElementTypeStaticText")
    private List<WebElement> navitems;

    public static HomeIOSPage visit(AppiumDriver driver) {
        return new HomeIOSPage(driver);
    }

    public HomeIOSPage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToSignIn() {
        getElement(menu).click();
        getElement(signIn).click();
    }

    public Boolean isSignedIn() {
        getElement(menu).click();
        getElements(navitems).waitForPresent();
        return ((Integer) navitems.size()).equals(3);
    }

    public Boolean isNotSignedIn() {
        getElement(menu).click();
        getElements(navitems).waitForPresent();
        return ((Integer) navitems.size()).equals(1);
    }

}
