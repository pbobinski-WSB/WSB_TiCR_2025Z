package org.example;

import java.time.Duration;
import java.util.List;

import org.example.config.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumExample {

    private SeleniumConfig config;
    private String url = "http://www.baeldung.com/";

    public SeleniumExample() {
        config = new SeleniumConfig();
        config.getDriver()
            .get(url);
        acceptCookies(config.getDriver());
    }

public static void acceptCookies(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        By acceptButtonLocator = By.xpath("//button[contains(text(), 'Zaakceptować')]");
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptButtonLocator)).click();
            System.out.println("Przycisk akceptacji ciasteczek został kliknięty.");
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Przycisk akceptacji ciasteczek nie pojawił się w ciągu 2 sekund.");
        }
    }

    public void closeWindow() {
        this.config.getDriver()
            .close();
    }

    public String getTitle() {
        return this.config.getDriver()
            .getTitle();
    }

    public void getAboutBaeldungPage() {
        closeOverlay();
        clickAboutLink();
        //clickAboutUsLink();
    }

    private void closeOverlay() {
        List<WebElement> webElementList = this.config.getDriver()
            .findElements(By.tagName("a"));
        if (webElementList != null) {
            webElementList.stream()
                .filter(webElement -> "Close".equalsIgnoreCase(webElement.getAttribute("title")))
                .filter(WebElement::isDisplayed)
                .findAny()
                .ifPresent(WebElement::click);
        }
    }

    private void clickAboutLink() {
        Actions actions = new Actions(config.getDriver());
        WebElement aboutElement = this.config.getDriver()
            .findElement(By.id("menu-item-6138"));
        
        actions.moveToElement(aboutElement).perform();
    }

    private void clickAboutUsLink() {
        WebElement element = this.config.getDriver()
            .findElement(By.partialLinkText("About Baeldung."));
        element.click();
    }

    public boolean isAuthorInformationAvailable() {
        return this.config.getDriver()
            .getPageSource()
            .contains("Eugen – an engineer");
    }

}
