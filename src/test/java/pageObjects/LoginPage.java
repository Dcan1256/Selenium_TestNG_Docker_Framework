package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ResourceBundle;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Web Elements ------------------------------------------------------------------------

    //UserName Box
    @FindBy(xpath = "//*[@id=\"user-name\"]")
    public WebElement userNameBox;

    //Password Box
    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement passwordBox;

    //Login Button
    @FindBy(xpath = "//*[@id=\"login-button\"]")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/div[3]/h3")
    public WebElement errorMsg;

    //Action Methods ----------------------------------------------------------------------
    public void loginWithConfigFile(){
        ResourceBundle config=ResourceBundle.getBundle("config");

        userNameBox.sendKeys(config.getString("username"));
        passwordBox.sendKeys(config.getString("pwd"));
        loginButton.click();
    }

    public void login(String username, String password) {
        userNameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginButton.click();
    }
}
