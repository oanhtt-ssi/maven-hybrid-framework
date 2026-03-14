package employee;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.Status;
import core.BasePage;
import core.BaseTest;
import io.qameta.allure.*;
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

import java.time.Duration;

@Epic("OrangeHRM")
@Feature("Employee")
public class Level_16_Allure extends BaseTest {
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

    @Description("Create new an Employee")
    @Story("ITB-111 - Employee CRUD")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void Employee_01_CreateEmployee(){

        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        dashboardPage = loginPage.clickToLoginButton();
        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);

        employeeListPOPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPOPage.isLoadingSpinnerDisappear(driver));
        Assert.assertTrue(employeeListPOPage.isPIMHeaderDisplayed());


    }

    @Description("View existing Employee")
    @Story("ITB-111 - Employee CRUD")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void Employee_02_ViewEmployee(){

        addEmployeePage = employeeListPOPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToLastNameTextbox(lastName);
        employeeID = addEmployeePage.getEmployeeIDValue();
        addEmployeePage.sleepInSecond(2);
    }

    @Description("Edit an Employee")
    @Story("ITB-111 - Employee CRUD")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void Employee_03_EditEmployee(){

        personalDetailPage = addEmployeePage.clickToSaveButton();
        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), lastName);
    }

    @Description("Delete Employee")
    @Story("ITB-111 - Employee CRUD")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void Employee_04_RemoveEmployee(){

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
