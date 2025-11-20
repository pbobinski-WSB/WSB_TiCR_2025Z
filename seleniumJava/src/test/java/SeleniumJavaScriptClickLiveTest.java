import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.SeleniumExample;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.Duration;

public class SeleniumJavaScriptClickLiveTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1500));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @After
    public void cleanUp() {
        driver.close();
    }

    @Test
    public void whenSearchForSeleniumArticles_thenReturnNotEmptyResults() {
        driver.get("https://baeldung.com");
        SeleniumExample.acceptCookies(driver);
        String title = driver.getTitle();
        assertEquals("Baeldung", title);

        wait.until(ExpectedConditions.elementToBeClickable(By.className("nav--menu_item_anchor")));
        WebElement searchButton = driver.findElement(By.className("nav--menu_item_anchor"));
        clickElement(searchButton);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("search")));
        WebElement searchInput = driver.findElement(By.id("search"));
        searchInput.sendKeys("Selenium");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-search")));
        WebElement seeSearchResultsButton = driver.findElement(By.cssSelector(".btn-search"));
        clickElement(seeSearchResultsButton);

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("post")));
        int seleniumPostsCount = driver.findElements(By.className("post"))
            .size();
        assertTrue(seleniumPostsCount > 0);
    }

    private void clickElement(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

}
