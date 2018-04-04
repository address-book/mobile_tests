package examples.pages;

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

public class BasePage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void click(WebElement element) {
        waitUntilElementPresent(element, "");
        element.click();
    }

    void sendKeys(WebElement element, String text) {
        waitUntilElementPresent(element, "");
        element.sendKeys(text);
    }

    String getText(WebElement element) {
        waitUntilElementPresent(element, "");
        return element.getText();
    }

    protected Boolean exits(WebElement element) {
        try {
            element.isEnabled(); // any wire call will work
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    protected Boolean eventuallyExists(WebElement element) {
        try {
            waitUntilElementPresent(element, "");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void waitWhileElementPresent(WebElement element, String message) {
        try {
            await().atMost(30, SECONDS).until(() -> !exits(element));
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException(message, e);
        }
    }

    protected void waitUntilElementPresent(WebElement element, String message) {
        try {
            await().atMost(30, SECONDS).until(() -> exits(element));
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException(message, e);
        }
    }
}
