package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import testBase.BaseClass;

/**
 * Author:
 * Project:
 *
 * Description: This Class is the base constructor for all pages we will have in our project
 * We will use one Driver instance for all pages, this BasePage will be the parent for all of our pages
 */
public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
