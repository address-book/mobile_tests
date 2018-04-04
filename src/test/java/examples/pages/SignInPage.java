package examples.pages;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        super(driver);
    }

    public void signInSuccessfully(UserData user) {
        signIn(user);
        getElement(submit).
                waitWhilePresent("Form should have been submitted, but it appears not to have worked");
    }

    public void signInUnsuccessfully(UserData user) {
        signIn(user);
        getElement(alert).
                waitUntilPresent("Form submission should have caused an error, but none was found");
    }

    private void signIn(UserData user) {
        getElement(emailField).sendKeys(user.getEmail());
        getElement(passwordField).sendKeys(user.getPassword());
        getElement(submit).click();
    }

    public void navigateToSignUp() {
        getElement(signUpLink).click();
    }

    public Boolean hasAlertNotice() {
        return getElement(alert).eventuallyExists();
    }
}
