package employee;

import core.BasePage;
import core.BaseTest;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import java.time.Duration;

public class Level_13_Logging extends BaseTest {
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
    String firstName, lastName, fullName, emailAddress, password, employeeID, adminUsername, adminPassword;




    @Parameters({"Browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
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
    public void Employee_01_NewEmployee(){
        log.info("NewEmployee - STEP 01: Enter to Username and Password with info: " + adminUsername + " | " +  adminPassword);
        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);

        log.info("NewEmployee - STEP 02: Navigate to Dashboard page ");
        dashboardPage = loginPage.clickToLoginButton();
        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        dashboardPage.sleepInSecond(2);
        verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);

        log.info("NewEmployee - STEP 03: Navigate to Employee Search page ");
        employeeListPOPage = dashboardPage.clickToPIMModule();
        verifyFalse(employeeListPOPage.isLoadingSpinnerDisappear(driver));
        verifyTrue(employeeListPOPage.isPIMHeaderDisplayed());

        log.info("NewEmployee - STEP 04: Navigate to Add Employee page ");
        addEmployeePage = employeeListPOPage.clickToAddEmployeeButton();
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        log.info("NewEmployee - STEP 05: Enter to FirstName and LastName with info: " + firstName + " | " +  lastName);
        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToLastNameTextbox(lastName);
        employeeID = addEmployeePage.getEmployeeIDValue();
        addEmployeePage.sleepInSecond(2);

        log.info("NewEmployee - STEP 06: Navigate to Personal Details page ");
        personalDetailPage = addEmployeePage.clickToSaveButton();
        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        log.info("NewEmployee - STEP 07: Verify Employee infor displayed: " + firstName + " | " +lastName + " | " + employeeID);
        verifyEquals(personalDetailPage.getFirstnameTextboxValue(), lastName);
        verifyEquals(personalDetailPage.getLastnameTextboxValue(), firstName);
        verifyEquals(personalDetailPage.getEmployeeTextboxValue(), employeeID);


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
