package examples.pages;

import examples.elements.*;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class BasePage {
    protected AppiumDriver driver;
    protected WebDriverWait wait;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 0, TimeUnit.SECONDS), this);
    }

    Element getElement(WebElement element) {
        return new Element(driver, element);
    }

    ElementCollection getElements(List<WebElement> elements) {
        return new ElementCollection(driver, elements);
    }

    static Boolean isNative(AppiumDriver driver) {
        return driver.getContext().equals("NATIVE_APP");
    }

    Boolean isAndroid(AppiumDriver driver) {
        return !driver.getPlatformName().equals("iOS");
    }
}
