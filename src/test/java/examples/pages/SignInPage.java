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

    // Implement Elements


    public SignInPage(AndroidDriver driver) {
        // Implement Method
    }

    public HomePage signInSuccessfully(UserData user) {
        // Implement Method
    }

    public SignInPage signInUnsuccessfully(UserData user) {
        // Implement Method
    }

    public SignUpPage navigateToSignUp() {
        // Implement Method
    }

    public Boolean hasAlertNotice() {
        // Implement Method
    }
}
