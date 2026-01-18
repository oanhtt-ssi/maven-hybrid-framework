package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddEmployeePO extends BasePageFactory {
    private WebDriver driver;

    @FindBy (xpath = "//input[@name='firstName']")
    private WebElement firstnameTextbox;

    @FindBy (xpath = "//input[@name='lastName']")
    private WebElement lastnameTextbox;

    @FindBy (xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement employeeIDTextbox;

    @FindBy (xpath = "//p[text()='Create Login Details']/following-sibling::div//span")
    private WebElement createLoginDetailCheckbox;

    @FindBy (xpath = "//label[text()='Username']/parent::div/following-sibling::div/input")
    private WebElement userTextbox;

    @FindBy (xpath = "//label[text()='Password']/parent::div/following-sibling::div/input")
    private WebElement passwordTextbox;

    @FindBy (xpath = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input")
    private WebElement confirmPasswordTextbox;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy (xpath = "//p[text()='Successfully Saved']")
    private WebElement successfullSaveMessage;

    @FindBy (xpath = "//div[@class = 'oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;


    public AddEmployeePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, firstnameTextbox);
        senKeyToElement(driver, firstnameTextbox, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, lastnameTextbox);
        senKeyToElement(driver, lastnameTextbox, lastName);

    }

    public String getEmployeeIDValue() {
        waitElementVisible(driver, employeeIDTextbox);
        return getElementDOMProperty(driver, employeeIDTextbox, "value");

    }

    public void clickToCreateLoginDetailsCheckbox() {
        waitElementClickable(driver, createLoginDetailCheckbox);
        clickToElement(driver, createLoginDetailCheckbox);

    }

    public void enterToUserNameTextbox(String emailAddress) {
        waitElementInvisible(driver, userTextbox);
        senKeyToElement(driver, userTextbox, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementInvisible(driver, passwordTextbox);
        senKeyToElement(driver, passwordTextbox, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitElementInvisible(driver, confirmPasswordTextbox);
        senKeyToElement(driver, confirmPasswordTextbox, password);

    }

    public void clickToSaveButton() {
        waitElementClickable(driver, saveButton);
        clickToElement(driver, saveButton);

    }

    public boolean isSuccessfullySavedMessageDisplayed() {
        waitElementVisible(driver, successfullSaveMessage);
        return isElementDisplayed(driver, successfullSaveMessage);

    }

    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
