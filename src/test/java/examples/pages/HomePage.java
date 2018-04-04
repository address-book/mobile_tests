package examples.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    @AndroidFindBy(className = "android.widget.Button")
    private WebElement menu;

    @AndroidFindBy(id = "sign-in")
    private WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    private WebElement user;

    @AndroidFindBy(xpath = "//*[@resource-id='navbar']//*")
    private List<WebElement> navitems;

    public static HomePage visit(AndroidDriver driver) {
        if (!isNative(driver)) {
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

    public Boolean isSignedIn() {
        getElement(menu).click();
        if (isNative(driver)) {
            return ((Integer) navitems.size()).equals(4);
        } else {
            return getElement(user).eventuallyExists();
        }
    }

    public Boolean isNotSignedIn() {
        getElement(menu).click();
        if (isNative(driver)) {
            return ((Integer) navitems.size()).equals(2);
        } else {
            return getElement(signIn).eventuallyExists();
        }
    }

}
