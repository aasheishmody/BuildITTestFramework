@weatherforecastdemo
Feature: Weather Forecast Demo for Parallel Run

  This File has been created to demonstrate parallel run

  Background:
    Given I am on the 'Landing' page

  Scenario: 5 day weather forecast is displayed
    When I enter Perth as a city name
    Then the 5 day weather forecast for Perth is displayed

  Scenario: 3 hourly weather forecast is displayed
    And I enter Aberdeen as a city name
    When I select the first day in the 5 day weather forecast
    Then the 3 hourly weather forecast for the first day is displayed

  Scenario: 3 hourly weather forecast is not displayed
    When I enter Glasgow as a city name
    And I select the first day in the 5 day weather forecast
    And I reselect the first day in the 5 day weather forecast
    Then the 3 hourly weather forecast for the first day is not displayed

  Scenario: Daily forecast summarises the 3 hour data
    When I enter Edinburgh as a city name
    And I select the first day in the 5 day weather forecast
    Then the daily forecast summarises the 3 hour rounded down data

  Scenario: 3 hourly weather forecast displays the expected weather conditions
    When I enter Dundee as a city name
    And I select the first day in the 5 day weather forecast
    Then the 3 hourly weather forecast displays the expected weather conditions