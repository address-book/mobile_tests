package test.pages;

import test.data.UserData;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SignInPage extends BasePage {
    @FindBy(id = "session_email")
    private WebElement emailField;

    @FindBy(id = "session_password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement submit;

    @FindBy(className = "alert")
    private List<WebElement> alerts;

    public static SignInPage visit(AndroidDriver driver) {
        driver.get("http://a.testaddressbook.com/sign_in");
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

    public Boolean hasAlertNotice() {
        return ((Integer) alerts.size()).equals(1);
    }
}
