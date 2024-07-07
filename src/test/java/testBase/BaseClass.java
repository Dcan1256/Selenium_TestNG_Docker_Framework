package testBase;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager; // for log4j logger
import org.apache.logging.log4j.Logger; // for log4j logger
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Author:
 * Project:
 *
 * Description: This method will set up the foundation for the WebDriver which will be
 * required for all the test cases, it also allows both local machine and remote machine execution
 * with the desired capabilities.
 */

public class BaseClass {

    // The Static Driver Instance which will be shared across the class
    public static WebDriver driver;

    // The Logger Instance will be used for logging actions related with this class
    public Logger logger;


    @Parameters({"os","browser"}) // Passing OS and Browser Information From the XML Suite file
    @BeforeMethod(groups = {"Regression","Master","Sanity"}) // Add/Replace the Group based on your requirements !!!
    public void setUp(String os, String browser) throws MalformedURLException {
        // Reading the Properties file at .../resources/config.properties
        ResourceBundle config=ResourceBundle.getBundle("config");

        // Returning Class name internally to the log
        logger=LogManager.getLogger(this.getClass());

        // Local Machine Execution ______________________________________________________________________________
        if(config.getString("execution_env").equalsIgnoreCase("local")){
            /*
            To get rid of the headless running more remove options argument from drivers and comment the option
            initialization lines
             */

            // Chrome Browser...
            if(browser.equalsIgnoreCase("chrome")){
                //ChromeOptions options=new ChromeOptions().addArguments("--headless=new");
                driver=new ChromeDriver();
            }

            // Edge Browser...
            else if(browser.equalsIgnoreCase("edge")){
                //EdgeOptions options=new EdgeOptions().addArguments("--headless=new");
                driver=new EdgeDriver();
            }

            // Firefox Browser...
            else if(browser.equalsIgnoreCase("firefox")){
                //FirefoxOptions options=new FirefoxOptions().addArguments("--headless=new");
                driver=new FirefoxDriver();
            }

            // No Matching Browser !!!
            else {
                System.out.println("No Matching Browser !!!");
            }

            driver.manage().deleteAllCookies(); // Clearing the cookies !!!
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait for 10 seconds

            driver.get(config.getString("AppURL")); // Retrieve the application URL from the config.properties file
            driver.manage().window().maximize(); // Maximizing the size of the window...
        }


        // Remote Machine Execution ______________________________________________________________________________
        if(config.getString("execution_env").equalsIgnoreCase("remote")){

            // This object will store and pass required capabilities to the remote driver
            DesiredCapabilities capabilities=new DesiredCapabilities();

            // Setting Up the OS ------------------------------------------------------

            // WIN10 Operating System...
            if(os.equalsIgnoreCase("windows")){
                capabilities.setPlatform(Platform.WIN10);
            }

            // MAC Operating System...
            else if(os.equalsIgnoreCase("mac")){
                capabilities.setPlatform(Platform.MAC);
            }

            // LINUX Operating System...
            else if(os.equalsIgnoreCase("linux")){
                capabilities.setPlatform(Platform.LINUX);
            }

            // No-Matching Operating System...
            else{
                System.out.println("No Matching Operating System !!! ");
            }

            // Setting Up the Browser ------------------------------------------------------

            // Chrome Browser...
            if(browser.equalsIgnoreCase("chrome")){
                capabilities.setBrowserName("chrome");
            }

            // Firefox Browser...
            else if(browser.equalsIgnoreCase("firefox")){
                capabilities.setBrowserName("firefox");
            }

            // Edge Browser...
            else if(browser.equalsIgnoreCase("edge")){
                capabilities.setBrowserName("MicrosoftEdge");
            }

            /*
                Passing Selenium Grids address and Desired capabilities to the remote WebDriver for remote execution,
                Please replace ""http://172.17.0.1:4444/wd/hub" section if your Selenium Grid address is different from that
             */
            driver=new RemoteWebDriver(new URL("http://172.17.0.1:4444/wd/hub"),capabilities);

            driver.manage().deleteAllCookies(); // Clearing the cookies !!!
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait for 10 seconds

            driver.get(config.getString("AppURL")); // Retrieve the application URL from the config.properties file
            driver.manage().window().maximize(); // Maximizing the size of the window...

        }


    }

    @AfterMethod(groups = {"Regression","Master","Sanity"}) // Add/Replace the Group based on your requirements !!!
    public void tearDown(){
        // Closing all opened drivers at once
        driver.quit();

        // Closing the current driver (only one)
        //driver.close();
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile=new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }


}
