package examples.tests;

import examples.data.*;
import examples.pages.*;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class SignInSuccessTests extends BaseTest {

    @Test
    public void signInSuccessful() {
        SignInPage signInPage = SignInPage.visit(driver);
        signInPage.signInSuccessfully(UserData.validUser());

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.isSignedIn());
    }

}