package test.pages;

import test.data.UserData;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {
    private AndroidDriver driver;

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

    SignUpPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void signUp(UserData user) {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submit.click();
    }
}

