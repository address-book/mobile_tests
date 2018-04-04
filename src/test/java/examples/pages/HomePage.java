package examples.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    protected WebElement menu;

    @FindBy(css = "a[data-test=sign-in]")
    protected WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    protected WebElement user;

    public static HomePage visit(AppiumDriver driver) {
        driver.get("http://a.testaddressbook.com");
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
        return getElement(user).eventuallyExists();
    }

    public Boolean isNotSignedIn() {
        getElement(menu).click();
        return getElement(signIn).eventuallyExists();
    }

}
