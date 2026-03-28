package employee;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
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

public class Level_19_Element_Undisplayed extends BaseTest {
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



    }

    @Test
    public void Employee_01_NewEmployee(){

        loginPage.enterToTextboxByLabel(driver, "Username", adminUsername);
        loginPage.enterToTextboxByLabel(driver, "Password", adminPassword);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);

        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Maintenance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Buzz"));

        dashboardPage.clickToModuleByTextInMenuItem(driver, "PIM");
        employeeListPOPage = PageGenerator.getPage(EmployeeListPO.class, driver);
        verifyTrue(employeeListPOPage.isLoadingSpinnerDisappear(driver));
        verifyTrue(employeeListPOPage.isPIMHeaderDisplayed());

        employeeListPOPage.clickToButtonByText(driver, "Add");
        addEmployeePage = PageGenerator.getPage(AddEmployeePO.class, driver);
        verifyTrue(addEmployeePage.isLoadingSpinnerDisappear(driver));

        addEmployeePage.enterToTextboxByName(driver, "firstName",firstName);
        addEmployeePage.enterToTextboxByName(driver, "lastName",lastName);
        employeeID = addEmployeePage.getEmployeeIDValue();
        addEmployeePage.sleepInSecond(2);

        employeeID = addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.getTextboxValueByName(driver, "firstName");
        addEmployeePage.getTextboxValueByName(driver, "lastName");
        addEmployeePage.getTextboxValueByLabel(driver, "Employee Id");

        addEmployeePage.clickToCreateLoginDetailsCheckbox();
        addEmployeePage.sleepInSecond(2);

        addEmployeePage.enterToUserNameTextbox(emailAddress);
        addEmployeePage.enterToPasswordTextbox(password);
        addEmployeePage.enterToConfirmPasswordTextbox(password);


        addEmployeePage.clickToButtonByText(driver, "Save");
        personalDetailPage = PageGenerator.getPage(PersonalDetailPO.class, driver);

        verifyTrue(personalDetailPage.isToastMessageDisplayed(driver, "Successfully Saved"));

        verifyTrue(personalDetailPage.isLoadingSpinnerDisappear(driver));
        personalDetailPage.sleepInSecond(2);

        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "firstName"), firstName);
        verifyEquals(personalDetailPage.getTextboxValueByName(driver, "lastName"), lastName);
        verifyEquals(personalDetailPage.getTextboxValueByLabel(driver, "Employee Id"), employeeID);

        //Logout
        loginPage = personalDetailPage.clickLogoutOnTopMenu(driver);

        // Login lai bang quyen Employee vua tao ra
        loginPage.enterToTextboxByLabel(driver, "Username", emailAddress);
        loginPage.enterToTextboxByLabel(driver, "Password", password);
        loginPage.clickToButtonByText(driver, "Login");
        dashboardPage = PageGenerator.getPage(DashboardPO.class, driver);

        verifyTrue(dashboardPage.isLoadingSpinnerDisappear(driver));
        verifyTrue(dashboardPage.isDashboardHeaderDisplayed());
        dashboardPage.sleepInSecond(2);
        // Den man hinh Dashboard
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Leave"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Time"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "My Info"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Performance"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Dashboard"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Directory"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Claim"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemDisplayed(driver, "Buzz"));

        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver, "Admin"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver, "PIM"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver, "Recruitment"));
        verifyTrue(dashboardPage.isModuleByTextInMenuItemUndisplayed(driver, "Maintenance"));

    }




    @AfterClass
    public void afterClass(){
        driver.quit();

    }


}
