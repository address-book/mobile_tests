package test.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    @AndroidFindBy(className = "android.widget.Button")
    private static WebElement menu;

    @AndroidFindBy(id = "sign-in")
    private static WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    private static WebElement user;

    @AndroidFindBy(xpath = "//*[@resource-id='navbar']//*")
    private static List<WebElement> navitems;

    public static HomePage visit(AndroidDriver driver) {
        if (!driver.getContext().equals("NATIVE_APP")) {
            driver.get("http://a.testaddressbook.com");
        }
        return new HomePage(driver);
    }

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public void navigateToSignIn() {
        getElement(menu).click();
        getElement(signIn).click();
    }

    public Boolean isSignedIn(Boolean condition) {
        getElement(menu).click();
        if (!driver.getContext().equals("NATIVE_APP")) {
            if (condition) {
                getElement(user).waitForPresent();
            }
            return exists(user);
        } else {
            return ((Integer) navitems.size()).equals(4);
        }
    }
}

