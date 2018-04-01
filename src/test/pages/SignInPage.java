package test.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.data.UserData;

import java.util.List;

public class SignInPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @FindBy(css = "a[data-test=sign-up]")
    private WebElement signUpLink;

    @FindBy(id = "session_email")
    private WebElement emailField;

    @FindBy(id = "session_password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement submit;

    @FindBy(className = "alert")
    private List<WebElement> alerts;

    public SignInPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SignUpPage navigateToSignUp() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(signUpLink)).click();

        return new SignUpPage(driver);
    }

    public void signIn(UserData user) {
        wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).
                sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();
    }

    public Boolean hasAlertNotice() {
        return ((Integer) alerts.size()).equals(1);
    }
}
