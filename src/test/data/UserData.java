package test.data;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

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

    public static UserData blankPassword() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("password", "");

        return new UserData(data);
    }

    private String email;
    private String password;
    private Faker faker;

    public UserData(Map<String, String> defaultData) {
        faker = new Faker();

        email = defaultData.get("email");
        password = defaultData.get("password");

        this.email = (email != null) ? email : faker.internet().emailAddress();
        this.password = (password != null) ? password : faker.internet().password();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}

