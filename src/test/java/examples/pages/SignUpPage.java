package examples.pages;

import examples.data.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SignUpPage extends BasePage {
    @FindBy(id = "user_email")
    @iOSFindBy(xpath = "//XCUIElementTypeTextField")
    private WebElement emailField;

    @FindBy(id = "user_password")
    @iOSFindBy(xpath = "//XCUIElementTypeSecureTextField")
    private WebElement passwordField;

    @FindBy(name = "commit")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Sign up']")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Sign up' or @text='Sign up']")
    private WebElement submit;


    public static SignUpPage visit(AppiumDriver driver) {
        if (isNative(driver)) {
            SignInPage signInPage = SignInPage.visit(driver);
            signInPage.navigateToSignUp();
        } else {
            driver.get("http://a.testaddressbook.com/sign_up");
        }
        return new SignUpPage(driver);
    }

    public SignUpPage(AppiumDriver driver) {
        super(driver);
    }

    public void signUp(UserData user) {
        getElement(emailField).sendKeys(user.getEmail());
        getElement(passwordField).sendKeys(user.getPassword());
        getElement(submit).click();

        getElement(submit).
                waitWhilePresent("Form should have been submitted, but it appears not to have worked");
    }
}
