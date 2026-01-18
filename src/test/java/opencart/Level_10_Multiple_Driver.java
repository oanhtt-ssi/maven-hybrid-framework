package opencart;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminCustomerPO;
import pageObjects.openCart.admin.AdminDashboardPO;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.openCart.user.UserLoginPO;
import pageObjects.openCart.user.UserMyAccountPO;
import pageObjects.openCart.user.UserRegisterPO;

public class Level_10_Multiple_Driver extends BaseTest {
    private WebDriver userDriver;
    private WebDriver adminDriver;
    private String userUrl, adminUrl;

    private AdminCustomerPO adminCustomerPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminLoginPO adminLoginPage;
    private UserLoginPO userLoginPage;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserMyAccountPO userMyAccountPage;
    private String adminUser, adminPassword;
    private String userWindowID, adminWindowID;
    private String userFirstname, userLastname, userEmailAddress, userPassword;


    @Parameters({"Browser","userUrl","adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl){
        this.userUrl = userUrl;
        this.adminUrl = adminUrl;

        adminUser = "oanhtt";
        adminPassword = "Oanh222$$$";
        userFirstname = "Mirah";
        userLastname = "Carrie";
        userEmailAddress = "mirah.carrie" + getRandomNumber() + "@gmail.com";
        userPassword = "Auto111@@@";

        //Mo browser len la trang user
        userDriver = getBrowserDriver(browserName, userUrl);

        userHomePage = PageGenerator.getPage(UserHomePO.class, userDriver);

        adminDriver = getBrowserDriver(browserName, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, adminDriver);

    }


    @Test
    public void OpenCart_01_Multiple_Driver() {
        //Firefox cua user
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, userDriver);

        userRegisterPage = userLoginPage.clickToCotinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        // Firefox cua Admin chay

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());

        //Firefox cua user chay tiep
        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, userDriver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());



    }


    @AfterClass
    public void afterClass(){
        closeBrowser(userDriver);
        closeBrowser(adminDriver);

    }


}
