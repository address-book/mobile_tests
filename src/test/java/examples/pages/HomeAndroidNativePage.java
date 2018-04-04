package examples.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeAndroidNativePage extends HomePage {
    @AndroidFindBy(className = "android.widget.Button")
    protected WebElement menu;

    @AndroidFindBy(id = "sign-in")
    protected WebElement signIn;

    @AndroidFindBy(xpath = "//*[@resource-id='navbar']//*")
    private List<WebElement> navitems;

    public static HomeAndroidNativePage visit(AppiumDriver driver) {
        return new HomeAndroidNativePage(driver);
    }

    public HomeAndroidNativePage(AppiumDriver driver) {
        super(driver);
    }

    public void navigateToSignIn() {
        getElement(menu).click();
        getElement(signIn).click();
    }

    public Boolean isSignedIn() {
        getElement(menu).click();
        getElements(navitems).waitForPresent();
        return ((Integer) navitems.size()).equals(4);
    }

    public Boolean isNotSignedIn() {
        getElement(menu).click();
        getElements(navitems).waitForPresent();
        return ((Integer) navitems.size()).equals(2);
    }

}
