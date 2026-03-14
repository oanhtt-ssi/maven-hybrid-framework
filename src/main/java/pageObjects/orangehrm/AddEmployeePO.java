package pageObjects.orangehrm;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageObjects.orangehrm.editNavigation.PersonalDetailPO;
import pageUIs.orangehrm.AddEmployeeUI;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;
    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter to Firstname textbox with value: {0}")
    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeeUI.FIRSTNAME_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.FIRSTNAME_TEXTBOX, firstName);
    }

    @Step("Enter to Lastname textbox with value: {0}")
    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeeUI.LASTNAME_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.LASTNAME_TEXTBOX, lastName);
    }

    @Step("Get Employee ID from Add Employee page")
    public String getEmployeeIDValue() {
        waitElementPresence(driver, AddEmployeeUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver, AddEmployeeUI.EMPLOYEE_ID_TEXTBOX,"value");
    }

    @Step("Click to Save button and navigate to Personal Detail page")
    public void clickToCreateLoginDetailsCheckbox() {
        waitElementClickable(driver, AddEmployeeUI.CREATE_LOGIN_DETAIL_CHECKBOX);
        clickToElement(driver, AddEmployeeUI.CREATE_LOGIN_DETAIL_CHECKBOX);
    }

    public void enterToUserNameTextbox(String emailAddress) {
        waitElementVisible(driver, AddEmployeeUI.USER_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.USER_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, AddEmployeeUI.PASSWORD_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitElementVisible(driver, AddEmployeeUI.CONFIRM_PASSWORD_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public PersonalDetailPO clickToSaveButton() {
        waitElementClickable(driver, AddEmployeeUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeeUI.SAVE_BUTTON);
        waitListElementInvisible(driver, AddEmployeeUI.SPINNER_ICON);
        return PageGenerator.getPage(PersonalDetailPO.class,driver);
    }

    public boolean isSuccessfullySavedMessageDisplayed() {
        waitElementVisible(driver, AddEmployeeUI.SUCCESSFULL_SAVE_MESSAGE);
        return isElementDisplayed(driver, AddEmployeeUI.SUCCESSFULL_SAVE_MESSAGE);
    }
}
