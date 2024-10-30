package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private AppiumDriver driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All']")
    private MobileElement allTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Hotels']")
    private MobileElement hotelsTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Holidays']")
    private MobileElement holidaysTab;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void selectTab(String tab) {
        switch (tab.toLowerCase()) {
            case "all":
                allTab.click();
                break;
            case "hotels":
                hotelsTab.click();
                break;
            case "holidays":
                holidaysTab.click();
                break;
        }
    }


    public List<String> getOfferTexts() {
        List<MobileElement> elements = (List<MobileElement>) driver.findElements(By.xpath("//*[contains(@resource-id, 'content_card') and @class='android.widget.TextView']"));
        return elements.stream()
                .map(MobileElement::getText)
                .collect(Collectors.toList());
    }

    public boolean containsBothOfferTypes() {
        List<String> offers = getOfferTexts();
        return offers.stream().anyMatch(text -> text.startsWith("Hotel:")) &&
                offers.stream().anyMatch(text -> text.startsWith("Holiday:"));
    }

    public boolean isOffersDisplayed() {
        List<MobileElement> offers = driver.findElements(By.xpath("//*[contains(@resource-id, 'content_card')]"));
        return !offers.isEmpty();
    }

    public boolean hasTabs(String tabAll, String tabHotels, String tabHolidays) {
        return allTab.getText().equals(tabAll) &&
                hotelsTab.getText().equals(tabHotels) &&
                holidaysTab.getText().equals(tabHolidays);
    }
}
