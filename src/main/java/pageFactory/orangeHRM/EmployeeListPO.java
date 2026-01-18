package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EmployeeListPO extends BasePageFactory {
    private WebDriver driver;

    @FindBy (xpath = "//h6[text()='PIM']")
    private WebElement pimHeader;

    @FindBy (xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeButton;

    @FindBy (xpath = "//div[@class = 'oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;


    public EmployeeListPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPIMHeaderDisplayed() {
        waitElementVisible(driver, pimHeader);
        return isElementDisplayed(driver, pimHeader);

    }

    public void clickToAddEmployeeButton() {
        waitElementVisible(driver, addEmployeeButton);
        clickToElement(driver, addEmployeeButton);


    }

    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
