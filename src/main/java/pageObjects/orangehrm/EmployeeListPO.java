package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.EmployeeListUI;

public class EmployeeListPO extends BasePage {
    private WebDriver driver;
    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPIMHeaderDisplayed() {
        waitElementVisible(driver, EmployeeListUI.PIM_HEADER);
        return isElementDisplayed(driver, EmployeeListUI.PIM_HEADER);
    }

    public AddEmployeePO clickToAddEmployeeButton() {
        waitElementVisible(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        return PageGeneratorGeneric.getPage(AddEmployeePO.class,driver);

    }
}
