package steps;

import com.google.gson.JsonObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utilities.TestDataReader;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;
import static steps.Hooks.driver;

public class LoginSteps {
    private LoginPage loginPage;
    private JsonObject loginData;
    private HomePage homePage;


    @Given("the app is launched")
    public void appIsLaunched() throws FileNotFoundException {
        loginData = TestDataReader.getLoginData();
        loginPage = new LoginPage(driver);
        assertTrue("Username field is not visible", loginPage.isUsernameFieldVisible());
        assertTrue("Password field is not visible", loginPage.isPasswordFieldVisible());
        assertTrue("Calendar field is not visible", loginPage.isDateOfBirthFieldVisible());
    }

    @When("the user enters credentials from the file")
    public void enterCredentialsFromFile() {
        String username = loginData.get("username").getAsString();
        String password = loginData.get("password").getAsString();

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @When("the user selects date of birth from the file")
    public void selectDateOfBirthFromFile() {
        String dob = loginData.get("dateOfBirth").getAsString();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        LocalDate date = LocalDate.parse(dob, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
        String formattedDate = date.format(outputFormatter);

        loginPage.selectDateOfBirth(formattedDate);
    }


    @When("the user taps on the login button")
    public void tapLogin() {
        loginPage.tapLogin();
        homePage = new HomePage(driver);
    }

    @Then("the user should see the home screen with {string}, {string}, and {string} tabs")
    public void verifyHomeScreenTabs(String tabAll, String tabHolidays, String tabHotels) {
        assertTrue(homePage.hasTabs(tabAll, tabHolidays, tabHotels));
    }

}
