package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.orangehrm.DashboardUI;

import java.util.List;

public class DashboardPO extends BasePageFactory {
    private WebDriver driver;

    @FindBy (xpath = "//span[text()='PIM']/parent::a")
    private WebElement pimModule;

    @FindBy (xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy (xpath = "//div[@class = 'oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;


    public DashboardPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isDashboardHeaderDisplayed() {
        waitElementVisible(driver, dashboardHeader);
        return isElementDisplayed(driver, dashboardHeader);
    }

    public void clickToPIMModule() {
        waitElementClickable(driver, pimModule);
        clickToElement(driver, pimModule);

    }
    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
