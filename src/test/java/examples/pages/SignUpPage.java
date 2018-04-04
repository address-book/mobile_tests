package examples.pages;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SignUpPage extends BasePage {
    @FindBy(id = "user_email")
    private WebElement emailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement submit;


    public static SignUpPage visit(AndroidDriver driver) {
        driver.get("http://a.testaddressbook.com/sign_up");
        return new SignUpPage(driver);
    }

    public SignUpPage(AndroidDriver driver) {
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
