package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Assert;

public class BaseClass {
    private static WebDriver driver;

    public static void initializeDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null; // Reset the driver instance
        }
    }
}

