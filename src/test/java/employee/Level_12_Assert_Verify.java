package employee;

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

import java.time.Duration;

public class Level_12_Assert_Verify extends BaseTest {
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

        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);

        employeeListPOPage = dashboardPage.clickToPIMModule();
        verifyTrue(employeeListPOPage.isLoadingSpinnerDisappear(driver));
        verifyTrue(employeeListPOPage.isPIMHeaderDisplayed());

        addEmployeePage = employeeListPOPage.clickToAddEmployeeButton();
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToLastNameTextbox(lastName);
        employeeID = addEmployeePage.getEmployeeIDValue();
        addEmployeePage.sleepInSecond(2);

        addEmployeePage.clickToCreateLoginDetailsCheckbox();
        addEmployeePage.sleepInSecond(2);

        addEmployeePage.enterToUserNameTextbox(emailAddress);
        addEmployeePage.enterToPasswordTextbox(password);
        addEmployeePage.enterToConfirmPasswordTextbox(password);

        personalDetailPage = addEmployeePage.clickToSaveButton();

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

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
        driver.quit();

    }


}
