package pageObjects.orangehrm;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGenerator;
import pageUIs.orangehrm.LoginUI;

public class LoginPO extends BasePage {
    private WebDriver driver;

    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToUsernameTextbox(String userName) {
        waitElementVisible(driver, LoginUI.USERNAME_TEXTBOX);
        senKeyToElement(driver, LoginUI.USERNAME_TEXTBOX,userName);

    }

    public void enterToPasswordTextbox(String password) {
        waitElementVisible(driver, LoginUI.PASSWORD_TEXTBOX);
        senKeyToElement(driver, LoginUI.PASSWORD_TEXTBOX,password);
    }

    public DashboardPO clickToLoginButton() {
        waitElementClickable(driver, LoginUI.LOGIN_BUTTON);
        clickToElement(driver,LoginUI.LOGIN_BUTTON);
        return PageGenerator.getPage(DashboardPO.class, driver);

    }

}
