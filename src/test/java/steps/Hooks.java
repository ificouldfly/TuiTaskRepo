package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.remote.DesiredCapabilities;
import pageobjects.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Hooks {
    public static AppiumDriver<MobileElement> driver;
    private LoginPage loginPage;

    @Before
    public void setUp(Scenario scenario) {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("automationName", "UiAutomator2");
        Path appPath = Paths.get("src", "test", "resources", "app-release.apk").toAbsolutePath();
        capabilities.setCapability("app", appPath.toString());

        try {
            driver = new AndroidDriver<>(new URL("http://192.168.1.142:4723"), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        loginPage = new LoginPage(driver);

        if (!scenario.getName().toLowerCase().contains("login")) {
            loginPage.loginWithDataFromFile();
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}