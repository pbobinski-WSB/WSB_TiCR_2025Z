package org.example.pages;

import org.example.config.SeleniumConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartHerePage {

    private SeleniumConfig config;

    @FindBy(xpath = "//title")
    private WebElement title;

    public StartHerePage(SeleniumConfig config) {
        this.config = config;
    }

    public String getPageTitle() {
        return config.getDriver().getTitle();
    }
}
