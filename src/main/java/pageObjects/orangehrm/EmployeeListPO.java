package pageObjects.orangehrm;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
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

    @Step("Click to Add Employee and navigate to Add Employee page")
    public AddEmployeePO clickToAddEmployeeButton() {
        waitElementVisible(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        clickToElement(driver, EmployeeListUI.ADD_EMPLOYEE_BUTTON);
        return PageGenerator.getPage(AddEmployeePO.class,driver);

    }
}
