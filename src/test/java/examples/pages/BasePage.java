package examples.pages;

import io.appium.java_client.android.AndroidDriver;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
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
        while(true) {
            try {
                element.click();
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitUntilElementPresent(element, "");
            }
        }
    }

    void sendKeys(WebElement element, String text) {
        while(true) {
            try {
                element.sendKeys(text);
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitUntilElementPresent(element, "");
            }
        }
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
