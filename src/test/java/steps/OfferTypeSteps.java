package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import pageobjects.HomePage;
import static org.junit.Assert.assertTrue;
import static steps.Hooks.driver;

public class OfferTypeSteps {
    private HomePage homePage;

    @Given("the user is logged in")
    public void userIsLoggedIn() {
        homePage = new HomePage(driver);
    }

    @When("the user navigates to the {string} tab")
    public void navigateToTab(String tab) {
        homePage.selectTab(tab);
    }

    @Then("offers are displayed")
        public void verifyOffersDisplayed(){
            assertTrue("Offers were not displayed on the tab", homePage.isOffersDisplayed());
        }

    @Then("there should be at least one {string} offer and one {string} offer on the {string} tab")
    public void verifyAtLeastOneOfEachOffer(String offerType1, String offerType2, String tab) {
        homePage.selectTab(tab);
        assertTrue("Expected both " + offerType1 + " and " + offerType2 + " offers on " + tab + " tab",
                homePage.containsBothOfferTypes());
    }

}
