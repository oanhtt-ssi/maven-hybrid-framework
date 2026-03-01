package pageObjects.gofile;

import core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.gofile.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePO extends BasePage {
    private WebDriver driver;

    public HomePO(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLoadingIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.LOADING_ICON);
    }

    public boolean isProgressBarIconDisappear() {
        return waitListElementInvisible(driver, HomePageUI.PROGRESS_BAR_ICON);
    }

    public String getSuccessLink() {
        waitElementInvisible(driver, HomePageUI.SUCCESS_CARD_LINK);
        return getElementText(driver, HomePageUI.SUCCESS_CARD_LINK);
    }

    public boolean isFileUploadedSuccess(String fileName) {
        waitElementVisible(driver, HomePageUI.UPLOADED_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.UPLOADED_FILE_NAME, fileName);

    }
}
