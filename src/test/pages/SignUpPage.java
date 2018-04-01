package test.pages;

import test.data.UserData;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {
    private AndroidDriver driver;

    @FindBy(id = "user_email")
    private static WebElement emailField;

    @FindBy(id = "user_password")
    private static WebElement passwordField;

    @FindBy(name = "commit")
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Sign up\"]")
    private WebElement submit;

    public static SignUpPage visit(AndroidDriver driver) {
        if (driver.getContext().equals("NATIVE_APP")) {
            SignInPage signInPage = SignInPage.visit(driver);
            signInPage.navigateToSignUp();
        } else {
            driver.get("http://a.testaddressbook.com/sign_up");
        }
        return new SignUpPage(driver);
    }

    public SignUpPage(AndroidDriver driver) {
        super(driver);
    }

    public void signUp(UserData user) {
        getElement(emailField).sendKeys(user.getEmail());
        getElement(passwordField).sendKeys(user.getPassword());
        getElement(submit).click();
    }
}

