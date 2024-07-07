package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Web Elements ------------------------------------------------------------------------
    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/span")
    public WebElement mainPageTittle;

    @FindBy(xpath = "//*[@id=\"header_container\"]/div[2]/div/span/select")
    public WebElement sortingDropDown;

    @FindBy(xpath = "//*[@id=\"shopping_cart_container\"]/a")
    public WebElement cartButton;

    @FindBy(xpath = "//*[@id=\"react-burger-menu-btn\"]")
    public WebElement burgerButton;

    @FindBy(xpath = "//*[@id=\"logout_sidebar_link\"]")
    public WebElement logoutLink;

    @FindAll(@FindBy(xpath = "//button[@class and text()='Add to cart']"))
    List<WebElement> addToCartButtonsList;

    @FindAll(@FindBy(xpath = "//button[@class and text()='Remove']"))
    List<WebElement> removeFromCartButtonsList;
    //Action Methods ----------------------------------------------------------------------
    public void addAll(){
        for(WebElement element:addToCartButtonsList){
            element.click();
        }
    }

    public void removeAll(){
        for(WebElement element:removeFromCartButtonsList){
            element.click();
        }
    }

}
