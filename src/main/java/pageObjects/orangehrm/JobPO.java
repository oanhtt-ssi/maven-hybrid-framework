package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.BasePageUI;
import pageUIs.orangehrm.JobPageUI;

public class JobPO extends BasePage {

    private WebDriver driver;
    public JobPO(WebDriver driver) {
        this.driver = driver;
    }

    public DependentsPO openDependentPage(){
        waitElementClickable(driver, BasePageUI.DEPENDENT_LINK);
        clickToElement(driver, BasePageUI.DEPENDENT_LINK);
        return PageGeneratorGeneric.getPage(DependentsPO.class, driver);

    }
}
