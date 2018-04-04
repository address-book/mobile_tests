package examples.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.TimeoutException;
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

    public void navigateToSignIn() {
        menu.click();
        signIn.click();
    }

    public Boolean isSignedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(menu)).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(user));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Boolean isNotSignedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(menu)).click();
        try {
            wait.until(ExpectedConditions.visibilityOf(signIn));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

}
