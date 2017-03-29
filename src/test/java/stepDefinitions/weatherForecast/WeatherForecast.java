package stepDefinitions.weatherForecast;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import pageObjects.*;

import static util.LoggerHelper.*;

public class WeatherForecast extends Page {

    private final LandingPage landingPage;

    public WeatherForecast() {
        landingPage = PageFactory.initElements(getDriver(), LandingPage.class);
    }

    @Given("^I am on the 'Landing' page$")
    public void iAmOnTheLandingPage() throws Throwable {
        navigating("to the Landing page", () -> landingPage.open());
    }

    @When("^I enter (.*) as a city name$")
    public void iEnterPerthAsACityName(String city) throws Throwable {
        entering("entering " + city + "as city name", () -> landingPage.enterCityName(city));
    }

    @Then("^the 5 day weather forecast for (.*) is displayed$")
    public void the5DayWeatherForecastForPerthIsDisplayed(String city) throws Throwable {
        asserting("that the 5 day weather forecast for " + city + " is displayed", () -> Assert.assertTrue(landingPage.fiveDayWeatherForecastIsDisplayed(city)));
    }

    @When("^I select the first day in the 5 day weather forecast$")
    public void iSelectTheFirstDayInThe5DayWeatherForecast() throws Throwable {
        selecting("the first day in the 5 day weather forecast", () -> landingPage.selectFirstDayInThe5DayWeatherForecast());
    }

    @Then("^the 3 hourly weather forecast for the first day is displayed$")
    public void the3HourlyWeatherForecastforthefirstdayIsDisplayed() throws Throwable {
        asserting("that the 3 hourly weather forecast for the first day is displayed", () -> Assert.assertTrue(landingPage.threeHourlyWeatherForecastForFirstDayIsDisplayed()));
    }

    @And("^I reselect the first day in the 5 day weather forecast$")
    public void iReselectTheFirstDayInThe5DayWeatherForecast() throws Throwable {
        reselecting("the first day in the 5 day weather forecast", () -> landingPage.reselectFirstDayInThe5DayWeatherForecast());
    }

    @Then("^the 3 hourly weather forecast for the first day is not displayed$")
    public void the3HourlyWeatherForecastForTheFirstDayIsNotDisplayed() throws Throwable {
        asserting("that the 3 hourly weather forecast for the first day is not displayed", () -> Assert.assertTrue(landingPage.threeHourlyWeatherForecastForFirstDayIsNotDisplayed()));
    }

    @Then("^the daily forecast summarises the 3 hour rounded down data$")
    public void theDailyForecastSummarisesThe3HourRoundedDownData() throws Throwable {
        asserting("that the daily forecast summarises the 3 hour rounded down data", () -> Assert.assertTrue(landingPage.dailyForecastSummarises3HourRoundedDownData()));
    }

    @Then("^the 3 hourly weather forecast displays the expected weather conditions$")
    public void the3HourlyWeatherForecastDisplaysTheExpectedWeatherConditions() throws Throwable {
        asserting("that the 3 hourly weather forecast displays the expected weather conditions", () -> Assert.assertTrue(landingPage.threeHourlyWeatherForecastDisplaysExpectedWeatherConditions()));
    }
}
