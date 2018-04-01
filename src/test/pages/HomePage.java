package test.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    @AndroidFindBy(accessibility = "Toggle navigation")
    private static WebElement menu;

    @AndroidFindBy(id = "sign-in")
    private static WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    private static List<WebElement> users;

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

    public Boolean isSignedIn() {
        getElement(menu).click();
        if (!driver.getContext().equals("NATIVE_APP")) {
            waitForPage("http://a.testaddressbook.com");
            return ((Integer) users.size()).equals(1);
        } else {
            return ((Integer) navitems.size()).equals(4);
        }
    }
}

