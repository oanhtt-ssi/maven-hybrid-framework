package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.orangehrm.ContactDetailPageUI;
import pageUIs.orangehrm.PersonalDetailUI;

public class ContactDetailPO extends BasePage {
    private WebDriver driver;
    public ContactDetailPO(WebDriver driver) {
        this.driver = driver;
    }

    public JobPO openJobPage(){
        waitElementClickable(driver, ContactDetailPageUI.JOB_LINK);
        clickToElement(driver, ContactDetailPageUI.JOB_LINK);
        return  PageGeneratorGeneric.getPage(JobPO.class,driver);
    }


}
