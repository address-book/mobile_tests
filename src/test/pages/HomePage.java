package test.pages;

import io.appium.java_client.android.AndroidDriver;
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
        HomePage page = new HomePage(driver);
        driver.get("http://a.testaddressbook.com");
        return page;
    }

    private HomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToSignIn() {
        this.menu.click();
        this.signIn.click();
    }

    public Boolean isSignedIn(String email) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(this.menu)).click();
        wait.until(ExpectedConditions.visibilityOf(this.user));

        return this.user.getText().equals(email);
    }
}

