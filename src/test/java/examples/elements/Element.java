package examples.elements;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class Element {
    private WebDriver driver;
    private WebElement element;
    private WebDriverWait wait;

    public Element(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public void click() {
        while (true) {
            try {
                element.click();
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitUntilPresent("");
            }
        }
    }

    public void sendKeys(String text) {
        while (true) {
            try {
                element.sendKeys(text);
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitUntilPresent("");
            }
        }
    }

    public Boolean exits() {
        try {
            element.isEnabled(); // any wire call will work
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    public Boolean eventuallyExists() {
        try {
            waitUntilPresent("");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitWhilePresent(String message) {
        try {
            await().atMost(30, SECONDS).until(() -> !exits());
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException(message, e);
        }
    }

    public void waitUntilPresent(String message) {
        try {
            await().atMost(30, SECONDS).until(() -> exits());
        } catch (ConditionTimeoutException e) {
            throw new TimeoutException(message, e);
        }
    }

}

