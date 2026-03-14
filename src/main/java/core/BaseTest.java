package core;


//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import reportConfigs.ExtentManager;

import java.time.Duration;
import java.util.Random;



public class BaseTest {
    private WebDriver driver;
//    protected final Logger log;
//
//    public BaseTest() {
//        this.log = LogManager.getLogger(getClass());
//    }


    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!!");
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        //driver.manage().window().maximize();
        return driver;

    }

    public WebDriver getDriver(){
        return this.driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid!!");
        }
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(url);
        //log.info("============ INIT BROWSER & DRIVER ============");
        return driver;
    }
    protected void closeBrowser(){
        if (null != driver){
            driver.quit();
        }
        //log.info("============ CLOSE BROWSER & DRIVER ============");
    }

    protected void closeBrowser(WebDriver driver){
        if (null != driver){
            driver.quit();
        }
        //log.info("============ CLOSE BROWSER & DRIVER ============");

    }
    protected int getRandomNumber(){
        return new Random().nextInt(999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            //log.info("------------------ PASSED ------------------");
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            //log.info("------------------ FAILED ------------------");
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
           // log.info("------------------ PASSED ------------------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
           // log.info("------------------ FAILED ------------------");
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            //log.info("------------------ PASSED ------------------");
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
            //log.info("------------------ FAILED ------------------");
        }
        return pass;
    }
//
//    protected void takeScreenshot(){
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//        ExtentManager.getTest().log(LogStatus.INFO, "Test Failed", ExtentManager.getTest().addBase64ScreenShot(base64Screenshot));
//    }

}
