package test.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private AndroidDriver driver;

    @FindBy(tagName = "button")
    private WebElement menu;

    @FindBy(css = "a[data-test=sign-in]")
    private WebElement signIn;

    @FindBy(css = "span[data-test=current-user]")
    private List<WebElement> users;


    public static HomePage visit(AndroidDriver driver) {
        HomePage page = new HomePage(driver);
        driver.get("http://a.testaddressbook.com");
        return page;
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
        wait.until(ExpectedConditions.urlMatches("http://a.testaddressbook.com"));

        return ((Integer) users.size()).equals(1);
    }
}

