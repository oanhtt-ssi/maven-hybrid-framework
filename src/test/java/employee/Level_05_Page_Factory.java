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
//import pageFactory.orangeHRM.*;

import java.time.Duration;

public class Level_05_Page_Factory extends BaseTest {
    private WebDriver driver;
    private BasePage basePage = BasePage.getBasePage();
//    private LoginPO loginPage;
//    private DashboardPO dashboardPage;
//    private EmployeeListPO employeeListPOPage;
//    private PersonalDetailPO personalDetailPage;
//    private AddEmployeePO addEmployeePage;
    String firstName, lastName, fullName, emailAddress, password, employeeID, adminUsername, adminPassword;



    @Parameters({"Browser","Url"})
    @BeforeClass
    public void beforeClass(String browserName, String url){
        driver = getBrowserDriver(browserName, url);

        firstName = "Mariah";
        lastName = "Carey";
        adminUsername = "oanhtt";
        adminPassword = "Oanh222$$$";
        fullName = firstName + " " + lastName;
        emailAddress= "mariah" + getRandomNumber() + "@hotmail.com";
        password = "Test@123";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

       // loginPage = new LoginPO(driver);

    }

    @Test
    public void Employee_01_NewEmployee(){

//        loginPage.enterToUsernameTextbox(adminUsername);
//        loginPage.enterToPasswordTextbox(adminPassword);
//        loginPage.clickToLoginButton();
//
//        dashboardPage = new DashboardPO(driver);
//        Assert.assertTrue(dashboardPage.isLoadingSpinnerDisappear());
//        Assert.assertTrue(dashboardPage.isDashboardHeaderDisplayed());
//
//        dashboardPage.clickToPIMModule();
//
//        employeeListPOPage = new EmployeeListPO(driver);
//        Assert.assertTrue(employeeListPOPage.isLoadingSpinnerDisappear());
//        Assert.assertTrue(employeeListPOPage.isPIMHeaderDisplayed());
//
//        employeeListPOPage.clickToAddEmployeeButton();
//
//        addEmployeePage = new AddEmployeePO(driver);
//        Assert.assertTrue(addEmployeePage.isLoadingSpinnerDisappear());
//        addEmployeePage.enterToFirstNameTextbox(firstName);
//        addEmployeePage.enterToLastNameTextbox(lastName);
//
//        employeeID = addEmployeePage.getEmployeeIDValue();
//
//        addEmployeePage.clickToCreateLoginDetailsCheckbox();
//
//        addEmployeePage.enterToUserNameTextbox(emailAddress);
//        addEmployeePage.enterToPasswordTextbox(password);
//        addEmployeePage.enterToConfirmPasswordTextbox(password);
//
//        addEmployeePage.clickToSaveButton();
//        addEmployeePage.sleepInSecond(2);
//
//        Assert.assertTrue(addEmployeePage.isSuccessfullySavedMessageDisplayed());
//
//        personalDetailPage = new PersonalDetailPO(driver);
//
//        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear());
//        Assert.assertTrue(personalDetailPage.isLoadingSpinnerDisappear());
//
//        Assert.assertEquals(personalDetailPage.getFirstnameTextboxValue(), firstName);
//        Assert.assertEquals(personalDetailPage.getLastnameTextboxValue(), lastName);
//        Assert.assertEquals(personalDetailPage.getEmployeeTextboxValue(), employeeID);
//

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
