package examples.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    private WebElement menu;

    @FindBy(css = "a[data-test=sign-in]")
    private WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    private WebElement user;


    public static HomePage visit(AndroidDriver driver) {
        driver.get("http://a.testaddressbook.com");
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
        return getElement(user).eventuallyExists();
    }

    public Boolean isNotSignedIn() {
        getElement(menu).click();
        return getElement(signIn).eventuallyExists();
    }

}
