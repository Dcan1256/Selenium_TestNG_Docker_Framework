package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends BasePage{

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }


    //Web Elements ------------------------------------------------------------------------
    @FindBy(xpath = "//*[@id=\"first-name\"]")
    public WebElement firstNameBox;

    @FindBy(xpath = "//*[@id=\"last-name\"]")
    public WebElement lastNameBox;

    @FindBy(xpath = "//*[@id=\"postal-code\"]")
    public WebElement zipCodeBox;

    @FindBy(xpath = "//*[@id=\"cancel\"]")
    public WebElement cancelButton;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    public WebElement continueButton;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement checkoutInfoTitle;

    //Action Methods ----------------------------------------------------------------------

    public void fillCheckoutInfo(String firstName, String lastName, String zipCode){
        firstNameBox.sendKeys(firstName);
        lastNameBox.sendKeys(lastName);
        zipCodeBox.sendKeys(zipCode);
        continueButton.click();
    }
}
