package examples.pages;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private AndroidDriver driver;

    @FindBy(css = "a[data-test=sign-up]")
    private WebElement signUpLink;

    @FindBy(id = "session_email")
    private WebElement emailField;

    @FindBy(id = "session_password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement submit;

    @FindBy(className = "alert")
    private WebElement alert;


    public SignInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage signInSuccessfully(UserData user) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();

        return new HomePage(driver);
    }

    public SignInPage signInUnsuccessfully(UserData user) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();

        return new SignInPage(driver);
    }

    public SignUpPage navigateToSignUp() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(signUpLink)).click();

        return new SignUpPage(driver);
    }

    public Boolean hasAlertNotice() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(alert)).click();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
