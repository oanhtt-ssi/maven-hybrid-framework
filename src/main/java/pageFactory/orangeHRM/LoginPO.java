package pageFactory.orangeHRM;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPO extends BasePageFactory {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameTextbox;

    @FindBy (name = "password")
    private WebElement passwordTextbox;

    @FindBy (xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy (xpath = "//div[@class = 'oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void enterToUsernameTextbox(String userName) {
        waitElementVisible(driver, usernameTextbox);
        senKeyToElement(driver, usernameTextbox, userName);

    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, passwordTextbox);
        senKeyToElement(driver, passwordTextbox, password);

    }

    public void clickToLoginButton() {
        waitElementClickable(driver, loginButton);
        clickToElement(driver, loginButton);

    }

    public boolean isLoadingSpinnerDisappear(){
        return waitListElementInvisible(driver, loadingSpinner);
    }
}
