package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utilities.TestDataReader;

import java.io.IOException;


public class LoginPage {
    private AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='username_input_field']")
    private MobileElement usernameField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='password_input_field']")
    private MobileElement passwordField;

    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='date_of_birth_field']")
    private MobileElement dateOfBirthField;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='date_of_birth_field_calendar_icon']/android.widget.Button")
    private MobileElement dateOfBirthCalendarIcon;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='date_of_birth_dialog']/android.view.View/android.view.View[1]/android.view.View[1]/android.widget.Button")
    private MobileElement pencilIcon;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private MobileElement dateEntryField;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='date_of_birth_dialog_confirm_button']")
    private MobileElement dateEntryConfirmButton;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='login_form_submit_button']")
    private MobileElement submitButton;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void selectDateOfBirth(String dob) {
        dateOfBirthCalendarIcon.click();
        pencilIcon.click();
        dateEntryField.click();
        dateEntryField.clear();
        dateEntryField.sendKeys(dob);
        dateEntryConfirmButton.click();
    }

    public boolean isUsernameFieldVisible() {
        return usernameField.isDisplayed();
    }

    public boolean isPasswordFieldVisible() {
        return passwordField.isDisplayed();
    }

    public boolean isDateOfBirthFieldVisible() {
        return dateOfBirthField.isDisplayed();
    }

    public void loginWithDataFromFile() {
        try {
            // Use TestDataReader to get the username and password
            String username = TestDataReader.getDataValue("username");
            String password = TestDataReader.getDataValue("password");

            // Use getFormattedDate to retrieve the formatted date of birth directly
            String dateOfBirth = TestDataReader.getFormattedDate("dateOfBirth", "yyyy-dd-MM", "MMddyyyy");

            // Perform login actions using the retrieved data
            enterUsername(username);
            enterPassword(password);
            selectDateOfBirth(dateOfBirth);
            tapLogin();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void tapLogin() {
        submitButton.click();
    }
}
