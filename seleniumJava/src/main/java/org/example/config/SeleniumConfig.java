package org.example.config;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SeleniumConfig {

    private ChromeDriver driver;

    public SeleniumConfig() {
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
        driver.manage().window().maximize();
    }

    public void close() {
        driver.close();
    }

    public void navigateTo(String url) {
        driver.navigate()
            .to(url);
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
