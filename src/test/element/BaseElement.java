package test.element;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseElement {
    private WebDriver driver;
    private WebElement element;
    private WebDriverWait wait;

    public BaseElement(WebDriver driver, WebElement element) {
        this.driver = driver;
        this.element = element;
    }

    public void click() {
        while(true) {
            try {
                element.click();
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitForElement(element);
            }
        }
    }

    public void sendKeys(String text) {
        while(true) {
            try {
                element.sendKeys(text);
                return;
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                waitForElement(element);
            }
        }
    }

    private void waitForElement(WebElement element) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}

