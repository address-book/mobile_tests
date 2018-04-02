package test.tests;

import org.testng.annotations.Test;
import test.data.UserData;
import test.pages.HomePage;
import test.pages.SignInPage;

import static org.testng.Assert.assertTrue;

public class SignInSuccess extends BaseTest {
    @Test
    public void signInSuccessful() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signIn(UserData.validUser());

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isSignedIn(true));
    }
}