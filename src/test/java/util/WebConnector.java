package util;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.picocontainer.classname.ClassName;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WebConnector {

    private static int shortTimeout;
    private static int mediumTimeout;
    private static int longTimeout;
    private static Logger logger;
    private static WebDriver driver;
    private static Properties properties;
    private static String baseURL;
    private static int height;
    private static int width;
    private static String browser;
    private static String operatingSystem;

    public static int getShortTimeout() {
        return shortTimeout;
    }

    public static void setShortTimeout(int shortTimeout) {
        WebConnector.shortTimeout = shortTimeout;
    }

    public static int getMediumTimeout() {
        return mediumTimeout;
    }

    public static void setMediumTimeout(int mediumTimeout) {
        WebConnector.mediumTimeout = mediumTimeout;
    }

    public static int getLongTimeout() {
        return longTimeout;
    }

    public static void setLongTimeout(int longTimeout) {
        WebConnector.longTimeout = longTimeout;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        WebConnector.logger = logger;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        WebConnector.driver = driver;
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        WebConnector.properties = properties;
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static void setBaseURL(String baseURL) {
        WebConnector.baseURL = baseURL;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        WebConnector.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        WebConnector.width = width;
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        WebConnector.browser = browser;
    }

    public static String getOperatingSystem() {
        return operatingSystem;
    }

    protected void initialize() {
        setOperatingSystem();
        initializeLogger();
        initializeProperties();
        setProperties();
        setBrowser();
        manageBrowser();
    }

    private void setOperatingSystem() {
        operatingSystem = System.getProperty("os.name");
    }

    private void initializeLogger() {
        setLogger(Logger.getLogger(ClassName.class.getName()));
    }

    private void initializeProperties() {
        getLogger().info("INITIALIZING PROPERTIES");
        setProperties(new Properties());
        try {
            getProperties().load(WebConnector.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("PROPERTIES INITIALIZED");
    }

    private void setProperties() {
        getLogger().info("SETTING PROPERTIES");
        String width;
        String height;
        String shortTimeout;
        String mediumTimeout;
        String longTimeout;
        setBaseURL(getProperties().getProperty("environment"));
        setBrowser(getProperties().getProperty("browser"));
        width = getProperties().getProperty("width");
        height = getProperties().getProperty("height");
        WebConnector.setWidth(Integer.parseInt(String.valueOf(width)));
        WebConnector.setHeight(Integer.parseInt(String.valueOf(height)));
        shortTimeout = getProperties().getProperty("shortTimeout");
        mediumTimeout = getProperties().getProperty("mediumTimeout");
        longTimeout = getProperties().getProperty("longTimeout");
        WebConnector.setShortTimeout(Integer.parseInt(String.valueOf(shortTimeout)));
        WebConnector.setMediumTimeout(Integer.parseInt(String.valueOf(mediumTimeout)));
        WebConnector.setLongTimeout(Integer.parseInt(String.valueOf(longTimeout)));
        getLogger().info("PROPERTIES SET");
    }

    private void setBrowser() {
        getLogger().info("SETTING BROWSER");
        switch (getBrowser()) {
            case "chrome":
                getLogger().info("Opening Chrome");
                if (getDriver() == null) {
                    ChromeDriverManager.getInstance().setup();
                    setDriver(new ChromeDriver());
                }
                break;
            case "firefox":
                if (getDriver() == null) {
                    FirefoxDriverManager.getInstance().setup();
                setDriver(new FirefoxDriver());
                }
                break;
            case "safari":
                if (driver == null) {
                setDriver(new SafariDriver());
                }
                break;
        }
        getLogger().info("BROWSER SET");
    }

    private void manageBrowser() {
        getLogger().info("MANAGING BROWSER");
        getDriver().manage().timeouts().pageLoadTimeout(getLongTimeout(), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(getMediumTimeout(), TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        getDriver().manage().window().setSize(new Dimension(getWidth(), getHeight()));
        getLogger().info("BROWSER MANAGED");
    }
}