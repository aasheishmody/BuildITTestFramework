package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends Page {

    @FindBy(id = "city")
    private WebElement CityTextBox;
    @FindBy(css = "#root>div>h1")
    private WebElement Header;
    @FindBy(xpath = "//span[contains(@data-test,'date-1')]/ancestor::div[1]")
    private WebElement FirstDayWeatherForecast;
    @FindBy(xpath = "//span[contains(@data-test,'date-2')]/ancestor::div[1]")
    private WebElement SecondDayWeatherForecast;
    @FindBy(xpath = "//span[contains(@data-test,'date-3')]/ancestor::div[1]")
    private WebElement ThirdDayWeatherForecast;
    @FindBy(xpath = "//span[contains(@data-test,'date-4')]/ancestor::div[1]")
    private WebElement FourthDayWeatherForecast;
    @FindBy(xpath = "//span[contains(@data-test,'date-5')]/ancestor::div[1]")
    private WebElement FifthDayWeatherForecast;
    @FindBy(xpath = "//span[contains(@data-test,'hour-1-1')]/ancestor::div[2]")
    private WebElement ThreeHourlyWeatherForecastForFirstDay;
    @FindBy(css = "span[data-test='day-1']")
    private WebElement FirstDay;
    @FindBy(css = "span[data-test='date-1']")
    private WebElement FirstDate;
    @FindBy(css = "#root > div > div:nth-child(2) > div.summary > span:nth-child(2) > svg[data-test*='description-1']")
    private WebElement FirstDayDominantWeatherCondition;
    @FindBy(css = "span[data-test='maximum-1']")
    private WebElement MaxTemperatureFirstDay;
    @FindBy(css = "span[data-test='minimum-1']")
    private WebElement MinTemperatureFirstDay;
    @FindBy(css = "span[data-test='speed-1']")
    private WebElement DominantWindSpeedFirstDay;
    @FindBy(css = "span[data-test='direction-1']")
    private WebElement DominantWindDirectionFirstDay;
    @FindBy(css = "span[data-test='rainfall-1']")
    private WebElement AggregateRainfallFirstDay;
    @FindBy(css = "span[data-test='pressure-1']")
    private WebElement AggregatePressureFirstDay;
    @FindBy(css = "span[data-test='hour-1-1']")
    private WebElement FirstDayFirstHour;
    @FindBy(css = "#root > div > div:nth-child(2) > div.details > div:nth-child(1) > span:nth-child(2) > svg[data-test*='description-1-1']")
    private WebElement FirstDayFirst3HourlyDominantWeatherCondition;
    @FindBy(css = "span[data-test='maximum-1-1']")
    private WebElement FirstDayFirst3HourlyMaxTemperature;
    @FindBy(css = "span[data-test='minimum-1-1']")
    private WebElement FirstDayFirst3HourlyMinTemperature;
    @FindBy(css = "span[data-test='speed-1-1']")
    private WebElement FirstDayFirst3HourlyDominantWindSpeed;
    @FindBy(css = "span[data-test='direction-1-1']")
    private WebElement FirstDayFirst3HourlyWindDirection;
    @FindBy(css = "span[data-test='rainfall-1-1']")
    private WebElement FirstDayFirst3HourlyAggregateRainfall;
    @FindBy(css = "span[data-test='pressure-1-1']")
    private WebElement FirstDayFirst3HourlyAggregatePressure;

    public void open() {
        getDriver().get(getBaseURL());
    }

    public void enterCityName(String city) {
        sendKeys(CityTextBox, city, getMediumTimeout() );
        sendKeyBoardKeys(CityTextBox, Keys.ENTER, getMediumTimeout());
    }

    public boolean fiveDayWeatherForecastIsDisplayed(String city) {
        return
        getText(Header, getMediumTimeout()).contentEquals("Five Day Weather Forecast for") &&
                getAttribute(CityTextBox, "value", getMediumTimeout()).contentEquals(city) &&
                isDisplayed(FirstDayWeatherForecast, getMediumTimeout()) &&
                isDisplayed(SecondDayWeatherForecast, getMediumTimeout()) &&
                isDisplayed(ThirdDayWeatherForecast, getMediumTimeout()) &&
                isDisplayed(FourthDayWeatherForecast, getMediumTimeout()) &&
                isDisplayed(FifthDayWeatherForecast, getMediumTimeout());
    }

    public void selectFirstDayInThe5DayWeatherForecast() {
        click(FirstDayWeatherForecast, getMediumTimeout());
    }

    public boolean threeHourlyWeatherForecastForFirstDayIsDisplayed() {
        return isDisplayed(ThreeHourlyWeatherForecastForFirstDay, getMediumTimeout());
    }

    public void reselectFirstDayInThe5DayWeatherForecast() {
        if (threeHourlyWeatherForecastForFirstDayIsDisplayed())
            click(FirstDayWeatherForecast, getMediumTimeout());
    }

    public boolean threeHourlyWeatherForecastForFirstDayIsNotDisplayed() {
        waitForElementToDisappear(ThreeHourlyWeatherForecastForFirstDay, getMediumTimeout());
        return isPresent(ThreeHourlyWeatherForecastForFirstDay);
    }

    public boolean dailyForecastSummarises3HourRoundedDownData() {
        return
              FirstDay.getText().matches("^[a-zA-Z]{3}$") &&
                      FirstDate.getText().matches("^[0-9]{2}$") &&
                      isDisplayed(FirstDayDominantWeatherCondition, getMediumTimeout()) &&
                      MaxTemperatureFirstDay.getText().matches("^\\d{1,2}°$") &&
                      MinTemperatureFirstDay.getText().matches("^\\d{1,2}°$") &&
                      DominantWindSpeedFirstDay.getText().matches("\\d+kph") &&
                      isDisplayed(DominantWindDirectionFirstDay, getMediumTimeout()) &&
                      AggregateRainfallFirstDay.getText().matches("\\d+mm") &&
                      AggregatePressureFirstDay.getText().matches("\\d+mb");
    }

    public boolean threeHourlyWeatherForecastDisplaysExpectedWeatherConditions() {
        return
                FirstDayFirstHour.getText().matches("^\\d{2}00$") &&
                        isDisplayed(FirstDayFirst3HourlyDominantWeatherCondition, getMediumTimeout()) &&
                        FirstDayFirst3HourlyMaxTemperature.getText().matches("^\\d{1,2}°$") &&
                        FirstDayFirst3HourlyMinTemperature.getText().matches("^\\d{1,2}°$") &&
                        FirstDayFirst3HourlyDominantWindSpeed.getText().matches("\\d+kph") &&
                        isDisplayed(FirstDayFirst3HourlyWindDirection, getMediumTimeout()) &&
                        FirstDayFirst3HourlyAggregateRainfall.getText().matches("\\d+mm") &&
                        FirstDayFirst3HourlyAggregatePressure.getText().matches("\\d+mb");
    }
}