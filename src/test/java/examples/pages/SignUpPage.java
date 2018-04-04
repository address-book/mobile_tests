package examples.pages;

import examples.data.*;

import io.appium.java_client.android.AndroidDriver;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class SignUpPage {
    private AndroidDriver driver;

    @FindBy(id = "user_email")
    private WebElement emailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    @FindBy(name = "commit")
    private WebElement submit;


    public static SignUpPage visit(AndroidDriver driver) {
        // implement method
    }

    public SignUpPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signUp(UserData user) {
        // Fix Method

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();

        // Add synchronization code
        // Add contextually useful error message
        return new HomePage(driver);
    }

    private void waitWhileElementPresent(WebElement element, String message) {
        try {
            await().atMost(30, SECONDS).until(() -> !exits(element));
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException(message, e);
        }
    }

    private Boolean exits(WebElement element) {
        try {
            element.isEnabled(); // any wire call will work
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }
}
