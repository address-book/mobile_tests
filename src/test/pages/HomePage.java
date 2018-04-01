package test.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {
    @FindBy(tagName = "button")
    private WebElement menu;

    @FindBy(css = "span[data-test=current-user]")
    private List<WebElement> users;


    public static HomePage visit(AndroidDriver driver) {
        driver.get("http://a.testaddressbook.com");
        return new HomePage(driver);
    }

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    public Boolean isSignedIn() {
        getElement(menu).click();
        waitForPage("http://a.testaddressbook.com");
        return ((Integer) users.size()).equals(1);
    }
}

