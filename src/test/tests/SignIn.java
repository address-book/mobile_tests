package test.tests;

import test.data.UserData;
import test.pages.*;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SignIn extends BaseTest {
    @Test
    public void signInSuccessful() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signIn(UserData.validUser());

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isSignedIn());
    }

    @Test
    public void signInBlankPasswordUnsuccessful() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signIn(UserData.blankPassword());

        assertTrue(signInPage.hasAlertNotice());
        assertFalse(HomePage.visit(driver).isSignedIn());
    }
}