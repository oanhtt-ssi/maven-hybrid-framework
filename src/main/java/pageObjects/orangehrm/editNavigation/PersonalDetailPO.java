package pageObjects.orangehrm.editNavigation;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.editNavigation.PersonalDetailUI;

public class PersonalDetailPO extends EditNavigatorPO {
    private WebDriver driver;
    public PersonalDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Get Firstname textbox attribute value")
    public String getFirstnameTextboxValue() {
        waitElementPresence(driver, PersonalDetailUI.FIRSTNAME_TEXTBOX);
        return getElementDOMProperty(driver, PersonalDetailUI.FIRSTNAME_TEXTBOX, "value");
    }

    @Step("Get Lastname textbox attribute value")
    public String getLastnameTextboxValue() {
        waitElementPresence(driver, PersonalDetailUI.LASTNAME_TEXTBOX);
        return getElementDOMProperty(driver, PersonalDetailUI.LASTNAME_TEXTBOX, "value");
    }

    @Step("Get Employee ID textbox attribute value")
    public String getEmployeeTextboxValue() {
        waitElementPresence(driver, PersonalDetailUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver, PersonalDetailUI.EMPLOYEE_ID_TEXTBOX, "value");
    }



}
