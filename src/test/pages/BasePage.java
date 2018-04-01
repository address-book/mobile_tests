package test.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    void click(WebElement element) {
        while(true) {
            try {
                element.click();
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitForElement(element);
            }
        }
    }

    String getText(WebElement element) {
        while(true) {
            try {
                return element.getText();
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitForElement(element);
            }
        }
    }

    void sendKeys(WebElement element, String text) {
        while(true) {
            try {
                element.sendKeys(text);
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitForElement(element);
            }
        }
    }

    void waitForPage(String url) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlMatches(url));
    }

    private void waitForElement(WebElement element) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}

