package examples.pages;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.awaitility.core.ConditionTimeoutException;

import static org.awaitility.Awaitility.await;

import static java.util.concurrent.TimeUnit.SECONDS;

public class SignInPage extends BasePage {
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


    public static SignInPage visit(AndroidDriver driver) {
        driver.get("http://a.testaddressbook.com/sign_in");
        return new SignInPage(driver);
    }

    public SignInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signInSuccessfully(UserData user) {
        signIn(user);
        waitWhileElementPresent(submit,
                "Form should have been submitted, but it appears not to have worked");
    }

    public void signInUnsuccessfully(UserData user) {
        signIn(user);

        waitUntilElementPresent(alert,
                "Form submission should have caused an error, but none was found");
    }

    private void signIn(UserData user) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();
    }

    public void navigateToSignUp() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(signUpLink)).click();
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
