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

public class Level_09_Switch_Url extends BaseTest {
    private WebDriver driver;
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
        driver = getBrowserDriver(browserName, userUrl);

        userHomePage = PageGenerator.getPage(UserHomePO.class, driver);

    }

    @Test(enabled = false)
    public void OpenCart_01_Logging_And_Logout(){
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToCotinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        userHomePage = userRegisterPage.clickToLogoutLinkAtUserSite(driver);

        //User => Admin
        userHomePage.openAdminSite(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

                adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin => User

        userHomePage = adminLoginPage.openUserSite(driver,userUrl);

        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User => Admin
        userMyAccountPage.openAdminSite(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

                adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        //Admin => User
        userHomePage = adminDashboardPage.openUserSite(driver,userUrl);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();


    }
    @Test (enabled = false)
    public void OpenCart_02_Logging_Without_Logout() {
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToCotinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());

        // User => Admin
        userRegisterPage.openAdminSite(driver, adminUrl);
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

                adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        //Admin => User
        userHomePage = adminCustomerPage.openUserSite(driver,userUrl);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        // User => Admin
        userMyAccountPage.openAdminSite(driver, adminUrl);
        adminDashboardPage = PageGenerator.getPage(AdminDashboardPO.class, driver);

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());


    }

    @Test
    public void OpenCart_03_Multipe_Tab(){
        userHomePage.clickToMyAccountAtFooter();
        userLoginPage = PageGenerator.getPage(UserLoginPO.class, driver);

        userRegisterPage = userLoginPage.clickToCotinueButton();

        userRegisterPage.enterToFirstName(userFirstname);
        userRegisterPage.enterToLastName(userLastname);
        userRegisterPage.enterToEmailAddress(userEmailAddress);
        userRegisterPage.enterToPassword(userPassword);
        userRegisterPage.acceptPrivacyCheckbox();
        userRegisterPage.clickContinueButton();

        Assert.assertTrue(userRegisterPage.isSuccessMessageDisplayed());
        userWindowID = userRegisterPage.getCurrentWindowID(driver);

        userRegisterPage.openUrlByNewTab(driver, adminUrl);

        // User => Admin
        adminLoginPage = PageGenerator.getPage(AdminLoginPO.class, driver);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());

        adminCustomerPage = adminDashboardPage.openCustomerPage();
        adminWindowID = adminCustomerPage.getCurrentWindowID(driver);


        //Admin => User
        adminCustomerPage.switchToWindowByID(driver, adminWindowID);

        userRegisterPage = PageGenerator.getPage(UserRegisterPO.class, driver);

        userHomePage = userRegisterPage.openHomeLogo(driver);

        userHomePage.clickToMyAccountAtFooter();
        userMyAccountPage = PageGenerator.getPage(UserMyAccountPO.class, driver);

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        // User => Admin
        userMyAccountPage.switchToWindowByID(driver, userWindowID);
        adminCustomerPage = PageGenerator.getPage(AdminCustomerPO.class, driver);


        Assert.assertTrue(adminCustomerPage.isCustomerHeaderDisplayed());

    }


    @AfterClass
    public void afterClass(){
        driver.quit();

    }


}
