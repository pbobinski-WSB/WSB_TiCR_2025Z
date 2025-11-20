import org.example.SeleniumExample;
import org.example.config.SeleniumConfig;
import org.example.models.BaeldungAbout;
import org.example.pages.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class SeleniumPageObjectLiveTest {

    private SeleniumConfig config;
    private BaeldungHomePage homePage;
    private BaeldungAbout about;

    @Before
    public void setUp() {
        config = new SeleniumConfig();
        homePage = new BaeldungHomePage(config);
        about = new BaeldungAbout(config);
        config.getDriver()
                .get("http://www.baeldung.com/");
        SeleniumExample.acceptCookies(config.getDriver());
    }

    @After
    public void teardown() {
       config.close();
    }

    @Test
    public void givenHomePage_whenNavigate_thenTitleMatch() {
        homePage.navigate();
        assertEquals(homePage.getPageTitle(), "Baeldung");
    }

    @Test
    public void givenHomePage_whenNavigate_thenShouldBeInStartHere() {
        homePage.navigate();
        StartHerePage startHerePage = homePage.clickOnStartHere();
        System.out.println();
        assertEquals(startHerePage.getPageTitle(), "Start Here | Baeldung");
    }

    @Test
    public void givenAboutPage_whenNavigate_thenTitleMatch() {
        about.navigateTo();
        assertEquals(about.getPageTitle(), "About Baeldung");
    }
}
