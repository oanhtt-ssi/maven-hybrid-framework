package pageObjects.orangehrm;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
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

    @Step("Click to PIM Module")
    public EmployeeListPO clickToPIMModule() {
        waitElementClickable(driver, DashboardUI.PIM_MODULE);
        clickToElement(driver,DashboardUI.PIM_MODULE);
        return PageGenerator.getPage(EmployeeListPO.class,driver);

    }
}
