package examples.pages;

import examples.elements.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class BasePage {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BasePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, 0, TimeUnit.SECONDS), this);
    }

    Element getElement(WebElement element) {
        return new Element(driver, element);
    }

    static Boolean isNative(AndroidDriver driver) {
        return driver.getContext().equals("NATIVE_APP");
    }

}
