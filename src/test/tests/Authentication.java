package test.tests;

import test.data.UserData;
import test.pages.*;

import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertFalse;

public class Authentication extends BaseTest {
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

    @Test
    public void signUpSuccessfully() {
        SignUpPage signUpPage = SignUpPage.visit(driver);
        signUpPage.signUp(UserData.randomUser());

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isSignedIn());
    }

}