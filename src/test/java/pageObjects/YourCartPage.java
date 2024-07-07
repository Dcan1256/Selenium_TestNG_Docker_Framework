package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import testBase.BaseClass;

import java.util.List;

public class YourCartPage extends BasePage {
    public YourCartPage(WebDriver driver) {
        super(driver);
    }

    //Web Elements ------------------------------------------------------------------------
    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement cartPageTitle;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a")
    public WebElement cartIcon;

    @FindAll(@FindBy(xpath = "//*[@id=\"cart_contents_container\"]/div/div[1]//div[contains(@class, 'cart_item')]/div[2]/div[2]/div"))
    public List<WebElement> prices;

    @FindAll(@FindBy(xpath = "//*[@id=\"cart_contents_container\"]//button[starts-with(@id, 'remove-')]"))
    public List<WebElement> removeButtons;

    @FindBy(xpath = "//*[@id=\"continue-shopping\"]")
    public WebElement continueShoppingButton;

    @FindBy(xpath = "//*[@id=\"checkout\"]")
    public WebElement checkoutButton;
    //Action Methods ----------------------------------------------------------------------
}
