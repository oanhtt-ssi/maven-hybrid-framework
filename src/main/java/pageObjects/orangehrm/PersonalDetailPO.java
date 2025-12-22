package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.PersonalDetailUI;

public class PersonalDetailPO extends BasePage {
    private WebDriver driver;
    public PersonalDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public String getFirstnameTextboxValue() {
        waitElementPresence(driver, PersonalDetailUI.FIRSTNAME_TEXTBOX);
        return getElementDOMProperty(driver, PersonalDetailUI.FIRSTNAME_TEXTBOX, "value");
    }

    public String getLastnameTextboxValue() {
        waitElementPresence(driver, PersonalDetailUI.LASTNAME_TEXTBOX);
        return getElementDOMProperty(driver, PersonalDetailUI.LASTNAME_TEXTBOX, "value");
    }

    public String getEmployeeTextboxValue() {
        waitElementPresence(driver, PersonalDetailUI.EMPLOYEE_ID_TEXTBOX);
        return getElementDOMProperty(driver, PersonalDetailUI.EMPLOYEE_ID_TEXTBOX, "value");
    }
}
