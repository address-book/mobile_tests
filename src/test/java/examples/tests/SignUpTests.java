package examples.tests;

import examples.data.*;
import examples.pages.*;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class SignUpTests extends BaseTest {

    @Test()
    public void signUpSuccessful() {
        SignUpPage signUpPage = SignUpPage.visit(driver);
        signUpPage.signUp(UserData.randomUser());

        assertTrue(pages.getHomePage().isSignedIn());
    }

}