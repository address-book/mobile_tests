package test.pages;

import io.appium.java_client.android.AndroidDriver;
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
        waitForElement(element).click();
    }

    void waitForPage(String url) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlMatches(url));
    }

    private WebElement waitForElement(WebElement element) {
        wait = new WebDriverWait(driver, 30);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
}

