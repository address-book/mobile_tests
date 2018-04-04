package examples.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private AndroidDriver driver;

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
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignInPage navigateToSignIn() {
        menu.click();
        signIn.click();
        return new SignInPage(driver);
    }

    public Boolean isSignedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(menu)).click();
        try {
            user.isEnabled();  // any wire call works
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
