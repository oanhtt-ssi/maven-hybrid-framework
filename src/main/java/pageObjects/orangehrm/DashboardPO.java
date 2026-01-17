package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.BasePageUI;
import pageUIs.orangehrm.DashboardUI;

public class DashboardPO extends BasePage {
    private WebDriver driver;
    public DashboardPO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashboardHeaderDisplayed() {

        waitElementVisible(driver, DashboardUI.DASHBOARD_HEADER);
        return isElementDisplayed(driver,DashboardUI.DASHBOARD_HEADER);
    }

    public EmployeeListPO clickToPIMModule() {
        waitElementClickable(driver, DashboardUI.PIM_MODULE);
        clickToElement(driver,DashboardUI.PIM_MODULE);
        return PageGeneratorGeneric.getPage(EmployeeListPO.class,driver);

    }
}
