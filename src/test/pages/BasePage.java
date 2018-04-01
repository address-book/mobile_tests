package test.pages;

import test.element.BaseElement;

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

    void waitForPage(String url) {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlMatches(url));
    }

    BaseElement getElement(WebElement element) {
        return new BaseElement(driver, element);
    }
}

