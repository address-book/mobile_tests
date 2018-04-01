package test.tests;

import org.testng.annotations.Test;
import test.data.UserData;
import test.pages.HomePage;
import test.pages.SignUpPage;

import static org.testng.Assert.assertTrue;

public class SignUp extends BaseTest {
    @Test
    public void signUpSuccessfully() {
        SignUpPage signUpPage = SignUpPage.visit(driver);
        signUpPage.signUp(UserData.randomUser());

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isSignedIn());
    }

}