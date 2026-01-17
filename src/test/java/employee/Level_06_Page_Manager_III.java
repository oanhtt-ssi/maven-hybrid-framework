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
import pageObjects.orangehrm.*;

import java.time.Duration;

public class Level_06_Page_Manager_III extends BaseTest {
    private WebDriver driver;
    private BasePage basePage = BasePage.getBasePage();
    private LoginPO loginPage;
    private DashboardPO dashboardPage;
    private EmployeeListPO employeeListPOPage;
    private PersonalDetailPO personalDetailPage;
    private AddEmployeePO addEmployeePage;
    private ContactDetailPO contactDetailPO;
    String firstName, lastName, fullName, emailAddress, password, employeeID, adminUsername, adminPassword;



    @Parameters({"Browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);


        loginPage = PageGeneratorManager.getLoginPage(driver);

        firstName = "Mariah";
        lastName = "Carey";
        adminUsername = "oanhtt";
        adminPassword = "Oanh222$$$";
        fullName = firstName + " " + lastName;
        emailAddress= "mariah" + getRandomNumber() + "@hotmail.com";
        password = "Test@123";
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));



    }

    @Test
    public void Employee_01_NewEmployee(){

        loginPage.enterToUsernameTextbox(adminUsername);
        loginPage.enterToPasswordTextbox(adminPassword);
        dashboardPage = loginPage.clickToLoginButton();

        Assert.assertTrue(dashboardPage.isLoadingIconDisappear(driver));
        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);

        employeeListPOPage = dashboardPage.clickToPIMModule();
        Assert.assertTrue(employeeListPOPage.isLoadingIconDisappear(driver));
        Assert.assertTrue(employeeListPOPage.isPIMHeaderDisplayed());

        addEmployeePage = employeeListPOPage.clickToAddEmployeeButton();
        Assert.assertTrue(addEmployeePage.isLoadingIconDisappear(driver));

        addEmployeePage.enterToFirstNameTextbox(firstName);
        addEmployeePage.enterToLastNameTextbox(lastName);
        employeeID = addEmployeePage.getEmployeeIDValue();

        addEmployeePage.clickToCreateLoginDetailsCheckbox();

        addEmployeePage.enterToUserNameTextbox(emailAddress);
        addEmployeePage.enterToPasswordTextbox(password);
        addEmployeePage.enterToConfirmPasswordTextbox(password);

        personalDetailPage = addEmployeePage.clickToSaveButton();

        Assert.assertTrue(personalDetailPage.isLoadingIconDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(), firstName);
        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), lastName);
        Assert.assertEquals(personalDetailPage.getEmployeeTextboxValue(), employeeID);


    }

    public void Employee_02_Contact_Detail(){
        contactDetailPO = personalDetailPage.openContactDetailPage(driver);
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
