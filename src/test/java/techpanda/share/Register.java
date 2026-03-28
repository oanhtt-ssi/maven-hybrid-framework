package techpanda.share;


import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import pageObjects.techpanda.RegisterPO;

import java.util.Set;


public class Register extends BaseTest {
    @Parameters({"Browser","Url"})
    @BeforeTest
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePage = PageGenerator.getPage(HomePO.class, driver);

        loginPage = homePage.openLoginPage();
        registerPage = loginPage.clickCreateAnAccount();

        registerPage.enterToFirstName("Tran");
        registerPage.enterToLastName("Oanh");
        registerPage.enterToEmail("oanhtt" + getRandomNumber() + "@hotmail.com");
        registerPage.enterToPassword("Oanh@2222");
        registerPage.enterToConfirmPassword("Oanh@2222");
        registerPage.clickToRegisterButton();

        //myAccountPage = registerPage.acceptContinueAlert();
        myAccountPage = PageGenerator.getPage(MyAccountPO.class, driver);

        verifyEquals(myAccountPage.getSuccessMessage(), "Thank you for registering with Main Website Store.");

        cookies = myAccountPage.getPageCookies(driver);

        closeBrowser();
    }



    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private RegisterPO registerPage;
    private MyAccountPO myAccountPage;
    public static Set<Cookie> cookies;



}
