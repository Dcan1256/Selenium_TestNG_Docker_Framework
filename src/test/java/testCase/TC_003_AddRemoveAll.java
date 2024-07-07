package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.MainPage;
import testBase.BaseClass;

public class TC_003_AddRemoveAll extends BaseClass {

    @Test
    public void addAll(){

        try {
            logger.info("***** Starting addAll test at TC_003_AddRemoveAll !!! *****");
            // Login-Pre requisite
            logger.info("----- Logging in... -----");
            LoginPage lp=new LoginPage(driver);
            lp.loginWithConfigFile();

            MainPage mp=new MainPage(driver);
            logger.info("----- Adding all items to the cart... -----");
            mp.addAll();

            logger.info("----- Getting the number of the items in the cart... -----");
            String actualValue=mp.cartButton.getText();
            String expectedValue="6";
            logger.info("----- Verifying number of items in the cart are correct... -----");
            Assert.assertEquals(actualValue,expectedValue);
            logger.info("***** SUCCESS !!! *****");
            logger.info("***** Ending addAll test at TC_003_AddRemoveAll !!! *****");
        }

        catch (Exception e){
            Assert.fail();
            logger.info("***** addAll test at TC_003_AddRemoveAll FAILED because of an Exception !!! *****");
        }
    }

    @Test
    public void removeAll(){
        try {
            logger.info("***** Starting removeAll test at TC_003_AddRemoveAll !!! *****");
            // Login Page --> Login-Pre requisite
            logger.info("----- Logging in... -----");
            LoginPage lp=new LoginPage(driver);
            lp.loginWithConfigFile();

            // Main Page -->
            MainPage mp=new MainPage(driver);
            logger.info("----- Adding all items to the cart... -----");
            mp.addAll(); // Add-all Pre requisite
            logger.info("----- Removing all items from the cart... -----");
            mp.removeAll();

            String actualValue=mp.cartButton.getText();
            String expectedValue="";
            logger.info("----- Verifying number of items in the cart are correct... -----");
            Assert.assertEquals(actualValue,expectedValue);
            logger.info("***** SUCCESS !!! *****");
            logger.info("***** Ending removeAll test at TC_003_AddRemoveAll !!! *****");
        }

        catch (Exception e){
            Assert.fail();
            logger.info("***** removeAll test at TC_003_AddRemoveAll FAILED because of an Exception !!! *****");
        }
    }
}
