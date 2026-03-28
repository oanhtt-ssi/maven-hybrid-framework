package techpanda;


import core.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.techpanda.HomePO;
import pageObjects.techpanda.LoginPO;
import pageObjects.techpanda.MyAccountPO;
import techpanda.share.Register;

import java.util.Set;


public class Level_20_Cookies extends BaseTest {
    @Parameters({"Browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        this.cookies = Register.cookies;

        homePage = PageGenerator.getPage(HomePO.class, driver);
        loginPage = homePage.openLoginPage();
        loginPage.setPageCookies(driver,this.cookies);
        myAccountPage.refreshCurrentPage(driver);

        myAccountPage = PageGenerator.getPage(MyAccountPO.class, driver);
        verifyEquals(myAccountPage.getMyAccountPageTitle(), "My Dashboard");
    }

    @Test
    public void Employee(){

    }

    @AfterClass
    public void afterClass(){
        closeBrowser();

    }

    private WebDriver driver;
    private HomePO homePage;
    private LoginPO loginPage;
    private MyAccountPO myAccountPage;
    private Set<Cookie> cookies;



}
