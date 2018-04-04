package examples.tests;

import examples.data.*;
import examples.pages.*;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class SignInErrorsTests extends BaseTest {

    @Test
    public void signInBlankPasswordUnsuccessful() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signInUnsuccessfully(UserData.blankPassword());

        assertTrue(signInPage.hasAlertNotice());
        assertTrue(pages.getHomePage().isNotSignedIn());
    }

}