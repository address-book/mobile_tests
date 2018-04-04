package examples.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    @iOSFindBy(accessibility = "Toggle navigation")
    @AndroidFindBy(className = "android.widget.Button")
    private WebElement menu;

    @iOSFindBy(accessibility = "Sign in")
    @AndroidFindBy(id = "sign-in")
    private WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    private WebElement user;

    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name='navigation']// XCUIElementTypeStaticText")
    @AndroidFindBy(xpath = "//*[@resource-id='navbar']//*")
    private List<WebElement> navitems;

    public static HomePage visit(AppiumDriver driver) {
        if (!isNative(driver)) {
            driver.get("http://a.testaddressbook.com");
        }
        return new HomePage(driver);
    }

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToSignIn() {
        getElement(menu).click();
        getElement(signIn).click();
    }

    public Boolean isSignedIn() {
        getElement(menu).click();
        if (isNative(driver)) {
            getElements(navitems).waitForPresent();
            Integer expectedValue = isAndroid(driver) ? 4 : 3;
            return ((Integer) navitems.size()).equals(expectedValue);
        } else {
            return getElement(user).eventuallyExists();
        }
    }

    public Boolean isNotSignedIn() {
        getElement(menu).click();
        if (isNative(driver)) {
            getElements(navitems).waitForPresent();
            Integer expectedValue = isAndroid(driver) ? 2 : 1;
            return ((Integer) navitems.size()).equals(expectedValue);
        } else {
            return getElement(signIn).eventuallyExists();
        }
    }

}
