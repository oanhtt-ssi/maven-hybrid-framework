package employee;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
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
import pageObjects.orangehrm.AddEmployeePO;
import pageObjects.orangehrm.DashboardPO;
import pageObjects.orangehrm.EmployeeListPO;
import pageObjects.orangehrm.LoginPO;
import pageObjects.orangehrm.editNavigation.ContactDetailPO;
import pageObjects.orangehrm.editNavigation.DependentsPO;
import pageObjects.orangehrm.editNavigation.JobPO;
import pageObjects.orangehrm.editNavigation.PersonalDetailPO;
import reportConfigs.ExtentManager;

import java.lang.reflect.Method;
import java.time.Duration;


public class Level_15_ChainTest extends BaseTest {
    private WebDriver driver;
    private BasePage basePage = BasePage.getBasePage();
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPOPage;
    private PersonalDetailPO personalDetailPage;
    private AddEmployeePO addEmployeePage;
    private ContactDetailPO contactDetailPage;
    private DependentsPO dependentPage;
    private JobPO jobPage;
    String firstName, lastName, fullName, emailAddress, password, employeeID, adminUsername, adminPassword, browserName;




    @Parameters({"Browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        this.browserName = browserName.toUpperCase();
        driver = getBrowserDriver(browserName, url);

        loginPage = PageGenerator.getPage(LoginPO.class, driver);

        firstName = "Mariah";
        lastName = "Carey";
        adminUsername = "oanhtt";
        adminPassword = "Oanh222$$$";
        fullName = firstName + " " + lastName;
        emailAddress= "mariah" + getRandomNumber() + "@hotmail.com";
        password = "Oanh@0506";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void Employee_01_CreateEmployee(){
        ChainTestListener.log("RUN ON BROWSER - " + browserName.toUpperCase());
        ExtentManager.getTest().log(Status.INFO,"Enter to Username and Password with info: " + adminUsername + " | " +  adminPassword);
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        ExtentManager.getTest().log(Status.INFO,"Navigate to Dashboard page ");
        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);

        ExtentManager.getTest().log(Status.INFO,"Navigate to Employee Search page ");
        employeeListPOPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPOPage.isLoadingSpinnerDisappear(driver));
        Assert.assertTrue(employeeListPOPage.isPIMHeaderDisplayed());


    }

    @Test
    public void Employee_02_ViewEmployee(){

        ChainTestListener.log("Navigate to Add Employee page ");
        addEmployeePage = employeeListPOPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        ChainTestListener.log("Enter to FirstName and LastName with info: " + firstName + " | " +  lastName);
        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToLastNameTextbox(lastName);
        employeeID = addEmployeePage.getEmployeeIDValue();
        addEmployeePage.sleepInSecond(2);
    }

    @Test
    public void Employee_03_EditEmployee(){

        ChainTestListener.log("Navigate to Personal Details page ");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), lastName);
    }

    @Test
    public void Employee_04_RemoveEmployee(){

        ChainTestListener.log("Verify Employee infor displayed: " + firstName + " | " +lastName + " | " + employeeID);
        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(), firstName);
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), lastName);
        Assert.assertEquals(personalDetailPage.getEmployeeTextboxValue(), lastName);
    }

    private Boolean isMessageSuccessDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElement(By.xpath("//p[text()='Successfully Saved']"))));
    }



    @AfterClass
    public void afterClass(){
        closeBrowser();

    }


}
