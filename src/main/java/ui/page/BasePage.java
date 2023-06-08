package ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;

    /* Конструктор */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /* Локаторы */
    protected final By headerConstructorButtonLocator = By.xpath("//nav//*[contains(text(),\"Конструктор\")]");
    protected final By headerLogoLocator = By.xpath("//nav/div");
    protected final By headerAccountButtonLocator = By.xpath("//nav//*[contains(text(),\"Личный Кабинет\")]");
}
