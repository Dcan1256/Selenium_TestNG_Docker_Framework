package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends BasePage{
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    //Web Elements ------------------------------------------------------------------------
    @FindBy (xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement checkoutCompleteTitle;

    @FindBy (xpath = "//*[@id=\"checkout_complete_container\"]/h2")
    public WebElement orderSuccessText;

    @FindBy (xpath = "//*[@id=\"back-to-products\"]")
    public WebElement backHomeButton;

    //Action Methods ----------------------------------------------------------------------
}
