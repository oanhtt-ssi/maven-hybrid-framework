package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.AddEmployeeUI;
import pageUIs.orangehrm.BasePageUI;

public class AddEmployeePO extends BasePage {
    private WebDriver driver;
    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeeUI.FIRSTNAME_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.FIRSTNAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeeUI.LASTNAME_TEXTBOX);
        senKeyToElement(driver, AddEmployeeUI.LASTNAME_TEXTBOX, lastName);
    }

    public String getEmployeeIDValue() {
        waitElementPresence(driver, AddEmployeeUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver, AddEmployeeUI.EMPLOYEE_ID_TEXTBOX,"value");
    }

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
        return PageGeneratorGeneric.getPage(PersonalDetailPO.class,driver);
    }

    public boolean isSuccessfullySavedMessageDisplayed() {
        waitElementVisible(driver, AddEmployeeUI.SUCCESSFULL_SAVE_MESSAGE);
        return isElementDisplayed(driver, AddEmployeeUI.SUCCESSFULL_SAVE_MESSAGE);
    }
}
