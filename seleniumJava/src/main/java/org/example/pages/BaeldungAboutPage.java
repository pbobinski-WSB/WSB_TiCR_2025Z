package org.example.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaeldungAboutPage {

    @FindBy(css = "h1")
    public static WebElement title;
}
