package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v85.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("http://www.python.org");
        System.out.println(driver.getTitle());


        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement searchBox = driver.findElement(By.name("q"));
        WebElement searchButton = driver.findElement(By.name("submit"));

        searchBox.sendKeys("Selenium");
        //searchBox.sendKeys(Keys.RETURN);
        searchButton.click();

        searchBox = driver.findElement(By.name("q"));
        String value = searchBox.getAttribute("value");
        System.out.println(value);

        //System.out.println(driver.getPageSource());

        WebElement element = driver.findElement(By.className("list-recent-events"));

        // Get all the elements available with tag name 'p'
        List<WebElement> elements = element.findElements(By.tagName("h3"));
        for (WebElement e : elements) {
            System.out.println(e.getText());
        }

        WebElement donate = driver.findElement(By.linkText("Donate"));
        Actions actionProvider = new Actions(driver);
        // Perform click-and-hold action on the element
        actionProvider.click(donate).build().perform();

        System.out.println(driver.getTitle());

    }
}