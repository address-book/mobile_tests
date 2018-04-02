package test.tests;

import test.data.UserData;
import test.pages.*;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class SignInErrors extends BaseTest {
    @Test
    public void signInBlankPasswordUnsuccessful() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signIn(UserData.blankPassword());

        assertTrue(signInPage.hasAlertNotice(true));
        assertFalse(HomePage.visit(driver).isSignedIn(false));
    }
}