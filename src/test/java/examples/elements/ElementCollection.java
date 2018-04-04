package examples.elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class ElementCollection {
    private WebDriver driver;
    private List<WebElement> elements;

    public ElementCollection(WebDriver driver, List<WebElement> elements) {
        this.driver = driver;
        this.elements = elements;
    }

    public void waitForPresent() {
        await().atMost(30, SECONDS).until(() -> elements.size() != 0);
    }

}
