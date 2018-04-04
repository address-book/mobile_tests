package examples.data;

import org.testng.annotations.DataProvider;
import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    private String email;
    private String password;
    private Faker faker = new Faker();

    public static UserData blankPassword() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("password", "");

        return new UserData(data);
    }

    public static UserData validUser() {
        Map<String, String> data = new HashMap<String, String>();
        data.put("email", "user@example.com");
        data.put("password", "password");

        return new UserData(data);
    }

    public static UserData randomUser() {
        Map<String, String> data = new HashMap<String, String>();
        return new UserData(data);
    }

    private UserData(Map<String, String> defaultData) {
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
