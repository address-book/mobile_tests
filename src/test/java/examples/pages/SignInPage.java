package examples.pages;

import examples.data.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    @FindBy(css = "a[data-test=sign-up]")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Sign up' or @text='Sign up']")
    @iOSFindBy(accessibility = "Sign up")
    private WebElement signUpLink;

    @FindBy(id = "session_email")
    @iOSFindBy(xpath = "//XCUIElementTypeTextField")
    private WebElement emailField;

    @FindBy(id = "session_password")
    @iOSFindBy(xpath = "//XCUIElementTypeSecureTextField")
    private WebElement passwordField;

    @FindBy(name = "commit")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Sign in']")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Sign in' or @text='Sign in']")
    private WebElement submit;

    @FindBy(className = "alert")
    @iOSFindBy(accessibility = "Bad email or password.")
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Bad email or password.' or @text='Bad email or password.']")
    private WebElement alert;


    public static SignInPage visit(AppiumDriver driver) {
        if (isNative(driver)) {
            HomePage homePage = HomePage.visit(driver);
            homePage.navigateToSignIn();
        } else {
            driver.get("http://a.testaddressbook.com/sign_in");
        }
        return new SignInPage(driver);
    }

    public SignInPage(AppiumDriver driver) {
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
