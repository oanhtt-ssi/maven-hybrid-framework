package pageObjects.orangehrm.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangehrm.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPO extends BasePage {
    WebDriver driver;

    public EditNavigatorPO(WebDriver driver) {
        this.driver = driver;
    }


    public JobPO openJobPage(WebDriver driver){
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver, EditNavigatorPageUI.JOB_LINK);
        return  PageGenerator.getPage(JobPO.class,driver);
    }


    public PersonalDetailPO openPersonalDetailPage(WebDriver driver){
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.PERSONAL_DETAIL_LINK);
        return PageGenerator.getPage(PersonalDetailPO.class, driver);
    }



    public DependentsPO openDependentPage(WebDriver driver){
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        clickToElement(driver, EditNavigatorPageUI.DEPENDENT_LINK);
        return PageGenerator.getPage(DependentsPO.class, driver);

    }

    public ContactDetailPO openContactDetailPage(WebDriver driver) {
        waitElementClickable(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGenerator.getPage(ContactDetailPO.class,driver);
    }

    public EditNavigatorPO openEditNavigatorPageByName(String pageName){
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);

        switch (pageName){
            case "Personal Details":
                return PageGenerator.getPage(PersonalDetailPO.class, driver);
            case "Dependents":
                return PageGenerator.getPage(DependentsPO.class, driver);
            case "Contact Details":
                return PageGenerator.getPage(ContactDetailPO.class, driver);
            case "Job":
                return PageGenerator.getPage(JobPO.class, driver);
            default:
                throw new IllegalArgumentException("Page name is not valid:" + pageName);
        }
    }
    public void openEditNavigatorByName(String pageName) {
        waitElementClickable(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, EditNavigatorPageUI.DYNAMIC_LINK_BY_PAGE_NAME, pageName);
    }

}
