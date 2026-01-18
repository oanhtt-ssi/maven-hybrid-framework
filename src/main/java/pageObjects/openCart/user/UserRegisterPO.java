package pageObjects.openCart.user;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.opencart.user.UserRegisterPageUI;

public class UserRegisterPO extends BasePage{
    WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }


    public void enterToFirstName(String firstname) {
        waitElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        senKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstname);
        
    }

    public void enterToLastName(String lastname) {
        waitElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        senKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastname);
    }

    public void enterToEmailAddress(String email) {
        waitElementVisible(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
        senKeyToElement(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX, email);
    }

    public void enterToPassword(String password) {
        waitElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        senKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void acceptPrivacyCheckbox( ) {
        waitElementClickable(driver, UserRegisterPageUI.AGREE_CHECKBOX);
        checkToCheckbox(driver, UserRegisterPageUI.AGREE_CHECKBOX);
    }

    public void clickContinueButton( ) {
        waitElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
        clickToElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
    }

    public boolean isSuccessMessageDisplayed(){
        waitElementVisible(driver, UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, UserRegisterPageUI.CREATED_ACCOUNT_SUCCESS_MESSAGE);
    }
}
