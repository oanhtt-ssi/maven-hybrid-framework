package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ContactDetailPO extends BasePageFactory {
    private WebDriver driver;

    @FindBy (xpath = "//input[@name='firstName']")
    private WebElement firstnameTextbox;

    @FindBy (xpath = "//input[@name='lastName']")
    private WebElement lastnameTextbox;

    @FindBy (xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy (xpath = "//div[@class = 'oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;


    public ContactDetailPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getFirstnameTextboxValue() {
        waitElementVisible(driver, firstnameTextbox);
        return getElementDOMProperty(driver, firstnameTextbox, "value");

    }

    public String getLastnameTextboxValue() {
        waitElementVisible(driver, lastnameTextbox);
        return getElementDOMProperty(driver, lastnameTextbox, "value");

    }

    public String getEmployeeTextboxValue() {
        waitElementVisible(driver, employeeIDTextbox);
        return getElementDOMProperty(driver, employeeIDTextbox, "value");

    }

    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
