package examples.data;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;

public class UserData {
    @DataProvider(name = "validUser")
    public static Object[][] valid() {
        return new Object[][] {{ "user@example.com", "password" }};
    }

    @DataProvider(name = "randomUser")
    public static Object[][] random() {
        Faker faker = new Faker();
        return new Object[][] {
                { faker.internet().emailAddress(), faker.internet().password() }
        };
    }
}
