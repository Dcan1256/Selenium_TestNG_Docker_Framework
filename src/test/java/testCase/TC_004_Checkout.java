package testCase;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testBase.BaseClass;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class TC_004_Checkout extends BaseClass {

    @Test
    public void cartTest(){

            try {
                    logger.info("***** Starting cartTest test at TC_004_Checkout !!! *****");
                    //Login Page --> Verified login pre-requisite
                    logger.info("----- Logging in... -----");
                    LoginPage lp=new LoginPage(driver);
                    lp.loginWithConfigFile();

                    //Main Page --> Add All items to the cart and go to Cart Page
                    MainPage mp=new MainPage(driver);
                    logger.info("----- Adding all items to the cart... -----");
                    mp.addAll();
                    logger.info("----- Getting the number of the items in the cart... -----");
                    Assert.assertEquals(mp.cartButton.getText(), "6"); // are there still 6 items?
                    logger.info("***** SUCCESS !!! *****");
                    mp.cartButton.click();
                    logger.info("----- Heading to the cart page... -----");
                    //Cart Page -->
                    YourCartPage ycp=new YourCartPage(driver);
                    logger.info("----- Verifying number of items in the cart are correct... -----");
                    Assert.assertEquals(ycp.cartIcon.getText(), "6"); // are there still 6 items?
                    logger.info("***** SUCCESS !!! *****");
                    logger.info("----- Calculating the total value of the items in the cart... -----");
                    ArrayList<Double> prices=new ArrayList<>();
                    for(WebElement element: ycp.prices){
                            String cost=element.getText();
                            prices.add(Double.valueOf(cost.replace("$","")));
                    }

                    double actualTotalCost= 0;
                    for (double cost: prices){
                            actualTotalCost=actualTotalCost+cost;
                    }
                    System.out.println(actualTotalCost);

                    ycp.checkoutButton.click();

                    logger.info("----- Entering Checkout Information... -----");
                    //Checkout Information Page -->
                    CheckoutInfoPage cip=new CheckoutInfoPage(driver);
                    ResourceBundle config=ResourceBundle.getBundle("config");
                    String firstName=config.getString("firstname");
                    String lastName=config.getString("lastname");
                    String zipCode=config.getString("zipcode");
                    cip.fillCheckoutInfo(firstName,lastName,zipCode);

                    //Checkout Overview Page -->
                    logger.info("----- Entering Checkout Overview page... -----");
                    CheckoutOverviewPage cop=new CheckoutOverviewPage(driver);
                    System.out.println(cop.itemTotalPrice.getText().substring(cop.itemTotalPrice.getText().indexOf("$")+1));
                    System.out.println(cop.taxTotal.getText().substring(cop.taxTotal.getText().indexOf("$")+1));
                    System.out.println(cop.finalTotalPrice.getText().substring(cop.finalTotalPrice.getText().indexOf("$")+1));
                    double itemTotal= Double.parseDouble((cop.itemTotalPrice.getText().substring(cop.itemTotalPrice.getText().indexOf("$")+1)));
                    double taxTotal= Double.parseDouble((cop.taxTotal.getText().substring(cop.taxTotal.getText().indexOf("$")+1)));
                    double totalSum= Double.parseDouble((cop.finalTotalPrice.getText().substring(cop.finalTotalPrice.getText().indexOf("$")+1)));
                    logger.info("----- Verifying all inclusive (tax and cost) value of items in the cart are correct... -----");
                    Assert.assertEquals(itemTotal+taxTotal,totalSum); // Make sure values at overview page are matching
                    logger.info("***** SUCCESS !!! *****");
                    logger.info("----- Verifying number of items in the cart are correct... -----");
                    Assert.assertEquals(cop.cartLogo.getText(),"6"); // Are there still 6 items in the cart?
                    logger.info("***** SUCCESS !!! *****");
                    logger.info("----- Verifying calculated total values of items in the cart are correct... -----");
                    Assert.assertEquals((actualTotalCost*100)/100+taxTotal,totalSum); // Total sum of the prices from cart page matching with a total sum at overview page
                    logger.info("***** SUCCESS !!! *****");
                    cop.finishButton.click();

                    //Checkout Complete Page -->
                    logger.info("----- Entering Checkout Complete page... -----");
                    CheckoutCompletePage ccp=new CheckoutCompletePage(driver);
                    logger.info("----- Verifying is checkout successful... -----");
                    Assert.assertEquals(ccp.checkoutCompleteTitle.getText(),"Checkout: Complete!"); // Checking the page title
                    logger.info("***** SUCCESS !!! *****");
                    logger.info("----- Verifying success message... -----");
                    Assert.assertEquals(ccp.orderSuccessText.getText(),"Thank you for your order!"); // Checking the success message
                    logger.info("***** SUCCESS !!! *****");
                    ccp.backHomeButton.click();

                    // Main Page --> logging out
                    logger.info("----- Entering Main page... -----");
                    logger.info("----- Verifying Main page title... -----");
                    Assert.assertTrue(mp.mainPageTittle.isDisplayed());
                    logger.info("***** SUCCESS !!! *****");
                    logger.info("----- Logging out... -----");
                    mp.burgerButton.click();
                    mp.logoutLink.click();

                    // Login Page -->
                    logger.info("----- Verifying Logged out successfully... -----");
                    Assert.assertTrue(lp.loginButton.isDisplayed()); // is the login button displayed?
                    logger.info("***** SUCCESS !!! *****");
            }
            catch (Exception e){
                    Assert.fail();
                    logger.info("***** cartTest test at TC_004_Checkout FAILED because of an Exception !!! *****");
            }

        }


}
