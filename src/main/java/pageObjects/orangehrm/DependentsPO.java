package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.BasePageUI;
import pageUIs.orangehrm.DependentsPageUI;

public class DependentsPO extends BasePage {

    private WebDriver driver;
    public DependentsPO (WebDriver driver) {
        this.driver = driver;
    }

    public PersonalDetailPO openPersonalDetailPage(){
        waitElementClickable(driver, BasePageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, BasePageUI.PERSONAL_DETAIL_LINK);
        return PageGeneratorGeneric.getPage(PersonalDetailPO.class, driver);
    }
}
