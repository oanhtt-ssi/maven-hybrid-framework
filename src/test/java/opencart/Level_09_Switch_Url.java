package opencart;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import pageObjects.orangehrm.*;
import pageObjects.orangehrm.editNavigation.ContactDetailPO;
import pageObjects.orangehrm.editNavigation.DependentsPO;
import pageObjects.orangehrm.editNavigation.JobPO;
import pageObjects.orangehrm.editNavigation.PersonalDetailPO;

import java.time.Duration;

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

    @Test
    public void OpenCart_01(){
        userLoginPage = userHomePage.clickToMyAccount();

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
        adminLoginPage = userHomePage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        adminCustomerPage = adminDashboardPage.openCustomerPage();

        adminLoginPage = adminCustomerPage.clickToLogoutLinkAtAdminSite(driver);

        //Admin => User

        userHomePage = adminLoginPage.openUserSite(driver,userUrl);

        userLoginPage = userHomePage.clickToMyAccount();

        userLoginPage.enterToEmailAddressTextbox(userEmailAddress);
        userLoginPage.enterToPasswordTextbox(userPassword);
        userMyAccountPage = userLoginPage.clickToLoginButton();

        Assert.assertTrue(userMyAccountPage.isMyAccountPageDisplayed());

        //User => Admin
        adminLoginPage = userMyAccountPage.openAdminSite(driver, adminUrl);

        adminLoginPage.enterToUsername(adminUser);
        adminLoginPage.enterToPassword(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();

        //Admin => User
        userHomePage = adminDashboardPage.openUserSite(driver,userUrl);


    }@Test
    public void OpenCart_02() {

    }


    @AfterClass
    public void afterClass(){
        driver.quit();

    }


}
