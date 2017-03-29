package pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Retrier;
import util.WebConnector;

public abstract class Page extends WebConnector {

    protected void click(WebElement element, int timeout) {
        Retrier.retry("click " + element, () -> singleClick(element, timeout), 5, 250l);
    }

    protected void singleClick(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
        getLogger().info("Waiting for element to be clickable - " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        getLogger().info("Clicking " + element);
        element.click();
        getLogger().info("Clicked " + element);
    }

    protected WebDriverWait buidWebDriverWait(int timeout) {
        long sleepInMillis = 250L;
        return new WebDriverWait(getDriver(), timeout, sleepInMillis);
    }

    protected void waitForElementToBeDisplayed(WebElement element, int timeout) {
        Retrier.retry("waitForElementToBeDisplayed " + element,
                () -> singleWaitForElementToBeDisplayed(element, timeout), 4, 250l);
    }

    protected void singleWaitForElementToBeDisplayed(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to be displayed");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        getLogger().info(element + " found after waiting for it to be displayed");
    }

    protected void sendKeys(WebElement element, String text, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + text + " to element - " + element);
        element.clear();
        element.sendKeys(text);
        getLogger().info("Sent text - " + text + " to element - " + element);
    }

    protected void sendKeyBoardKeys(WebElement element, Keys key, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Sending text - " + key + " to element - " + element);
        element.sendKeys(key);
        getLogger().info("Sent text - " + key + " to element - " + element);
    }

    protected String getText(WebElement element, int timeout) {
        Retrier.retry("getText " + element, () -> singleGetText(element, timeout), 4);
        return singleGetText(element, timeout);
    }

    protected String singleGetText(WebElement element, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting text from element - " + element);
        getLogger().info("Got text - " + element.getText() + " from element - " + element);
        return element.getText();
    }

    protected boolean isDisplayed(WebElement element, int timeout) {
        getLogger().info("Checking whether the "+element+" is displayed or not");
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Checked whether the "+element+" is displayed or not");
        return element.isDisplayed();
    }

    protected String getAttribute(WebElement element, String attribute, int timeout) {
        Retrier.retry("getAttribute " + element, () -> singleGetAttribute(element, attribute, timeout), 4, 100l);
        return singleGetAttribute(element, attribute, timeout);
    }

    protected String singleGetAttribute(WebElement element, String attribute, int timeout) {
        waitForElementToBeDisplayed(element, timeout);
        getLogger().info("Getting attribute - " + attribute + " from element - " + element);
        getLogger().info("Attribute - " + attribute + " of the element - " + element + " is" + element.getAttribute(attribute));
        return element.getAttribute(attribute);
    }

    protected void waitForElementToDisappear(WebElement element, int timeout) {
        getLogger().info("Waiting for " + element + " to disappear");
        WebDriverWait wait = buidWebDriverWait(timeout);
        wait.until(ExpectedConditions.invisibilityOf(element));
        getLogger().info("Waited for " + element + " to disappear");
    }

    protected Boolean isPresent(WebElement element) {
        getLogger().info("Checking if " + element + " is not displayed or not");
        Boolean displayed = true;
        try {
            if (element.isDisplayed())
                displayed = true;
        } catch (Throwable t) {
            displayed = false;
        }
        getLogger().info(element + " is displayed - " + displayed);
        return displayed;
    }
}