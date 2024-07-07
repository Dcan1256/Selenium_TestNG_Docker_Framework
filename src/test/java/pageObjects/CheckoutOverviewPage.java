package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage{
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    //Web Elements ------------------------------------------------------------------------
    @FindBy (xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement checkoutOverviewTitle;

    @FindBy (xpath = "//*[@id=\"shopping_cart_container\"]/a")
    public WebElement cartLogo;

    @FindBy (xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")
    public WebElement itemTotalPrice;

    @FindBy (xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[7]")
    public WebElement taxTotal;

    @FindBy (xpath = "//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]")
    public WebElement finalTotalPrice;

    @FindBy (xpath = "//*[@id=\"cancel\"]")
    public WebElement cancelButton;

    @FindBy (xpath = "//*[@id=\"finish\"]")
    public WebElement finishButton;
    //Action Methods ----------------------------------------------------------------------
}
