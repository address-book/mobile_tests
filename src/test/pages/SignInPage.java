package test.pages;

import test.data.UserData;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignInPage extends BasePage {
    @FindBy(id = "session_email")
    private static WebElement emailField;

    @FindBy(id = "session_password")
    private static WebElement passwordField;

    @FindBy(name = "commit")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Sign in' or @text='Sign in']")
    private static WebElement submit;

    @FindBy(className = "alert")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Bad email or password.' or @text='Bad email or password.']")
    private static List<WebElement> alerts;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Sign up' or @text='Sign up']")
    private static WebElement signUp;

    public static SignInPage visit(AndroidDriver driver) {
        if (driver.getContext().equals("NATIVE_APP")) {
            HomePage homePage = HomePage.visit(driver);
            homePage.navigateToSignIn();
        } else {
            driver.get("http://a.testaddressbook.com/sign_in");
        }
        return new SignInPage(driver);
    }

    public SignInPage(AndroidDriver driver) {
        super(driver);
    }

    public void signIn(UserData user) {
        getElement(emailField).sendKeys(user.getEmail());
        getElement(passwordField).sendKeys(user.getPassword());
        getElement(submit).click();
    }

    public void navigateToSignUp() {
        getElement(signUp).click();
    }

    public Boolean hasAlertNotice() {
        return ((Integer) alerts.size()).equals(1);
    }
}
