package core;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageObjects.orangehrm.EmployeeListPO;
import pageObjects.orangehrm.LoginPO;
import pageUIs.BasePageUI;
import pageUIs.orangehrm.DashboardUI;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BasePage {
    public static BasePage getBasePage(){
        return new BasePage();
    }
    public void openPageUrl(WebDriver driver, String Url){
        driver.get(Url);
    }

    public String getPageTitle(WebDriver driver){
       return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Set<Cookie> getPageCookies(WebDriver driver){
        return driver.manage().getCookies();
    }

    public void setPageCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie : cookies){
            driver.manage().addCookie(cookie);
        }
    }

    private Alert waitAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver){
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver){
        waitAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend){
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver){
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(long timeInSecond)  {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentWindowID(WebDriver driver){
        return driver.getWindowHandle();
    }

    public void openUrlByNewTab(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.TAB).get(url);
    }

    public void openUrlByNewWindow(WebDriver driver, String url){
        driver.switchTo().newWindow(WindowType.WINDOW).get(url);
    }

    public void switchToWindowByID(WebDriver driver, String windowID){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String expectedPageTitle){
        Set<String> allWindowIDs = driver.getWindowHandles();

        for (String id : allWindowIDs){
            driver.switchTo().window(id);
            sleepInSecond(1);
            if (driver.getTitle().contains(expectedPageTitle)){
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(WebDriver driver, String windowID){
        Set<String> allWindowIDs = driver.getWindowHandles();
        for (String id : allWindowIDs){
            if (!id.equals(windowID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }


    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private By getByLocator(String locatorType){
        if (locatorType == null || locatorType.trim().isEmpty()){
            throw new IllegalArgumentException("Locator type cannot be null or empty");
        }

        String[] locatorArr = locatorType.split("=",2);
        String locatorPrefix = locatorArr[0].trim();
        String locatorValue = locatorArr[1].trim();

        switch (locatorPrefix.toUpperCase()){
            case "ID":
                return By.id(locatorValue);
            case "CLASS":
                return By.className(locatorValue);
            case "NAME":
                return By.name(locatorValue);
            case "CSS":
                return By.cssSelector(locatorValue);
            case "XPATH":
                return By.xpath(locatorValue);
            default:
                throw new IllegalArgumentException("Locator type is not supported: " +locatorType);
        }
    }

    public WebElement getWebElement(WebDriver driver, String locatorType){
        return driver.findElement(getByLocator(locatorType));
    }

    public WebElement getWebElement(WebDriver driver, String locatorType, String... restValue){
        return driver.findElement(getByLocator(castParameter(locatorType, restValue)));
    }

    public List<WebElement> getListElement(WebDriver driver, String locatorType){
        return driver.findElements(getByLocator(locatorType));
    }

    public List<WebElement> getListElement(WebDriver driver, String locatorType, String... restValue){
        return driver.findElements(getByLocator(castParameter(locatorType, restValue)));
    }

    public void clickToElement(WebDriver driver, String locatorType){
        getWebElement(driver,locatorType).click();
    }

    public void clickToElement(WebDriver driver, String locatorType, String... restValue){
        getWebElement(driver, castParameter(locatorType, restValue)).click();
    }

    public void senKeyToElement(WebDriver driver, String locatorType, CharSequence keyToSend){
        getWebElement(driver, locatorType).clear();
        getWebElement(driver,locatorType).sendKeys(keyToSend);
    }

    public void senKeyToElement(WebDriver driver, String locatorType, CharSequence keyToSend, String... restValue){
        getWebElement(driver,castParameter(locatorType, restValue)).clear();
        getWebElement(driver,castParameter(locatorType, restValue)).sendKeys(keyToSend);
    }


    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem){
        new Select(getWebElement(driver,locator)).selectByVisibleText(valueItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem, String... restValue){
        new Select(getWebElement(driver,castParameter(locator, restValue))).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInSelectableDropDown(WebDriver driver,String parentLocator, String childLocator, String expectedTextItem) {

        clickToElement(driver, parentLocator);
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
        for (WebElement temp : allItems) {
            if (temp.getText().equals(expectedTextItem)) {
                temp.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public void selectItemInSelectableDropDown(WebDriver driver,String parentLocator, String childLocator, String expectedTextItem, String... restValue) {

        clickToElement(driver, castParameter(parentLocator, restValue));
        sleepInSecond(1);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
        for (WebElement temp : allItems) {
            if (temp.getText().equals(expectedTextItem)) {
                temp.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName){
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName, String... restValue){
        return getWebElement(driver, castParameter(locator, restValue)).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getDomAttribute(propertyName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName, String... restValue){
        return getWebElement(driver, castParameter(locator, restValue)).getDomAttribute(propertyName);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restValue){
        return getWebElement(driver, castParameter(locator, restValue)).getText();
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName){
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaColor){
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator){
        return getListElement(driver, locator).size();
    }

    public int getListElementNumber(WebDriver driver, String locator, String... restValue){
        return getListElement(driver, castParameter(locator, restValue)).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator){
        if (!isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public void checkToCheckbox(WebDriver driver, String locator, String... restValue){
        if (!isElementSelected(driver, castParameter(locator, restValue))){
            getWebElement(driver, castParameter(locator, restValue)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator){
        if (isElementSelected(driver, locator)){
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        boolean status= false;
        try {
            WebElement element = getWebElement(driver, locator);
            status = element.isDisplayed();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restValue){
        boolean status= false;
        try {
            WebElement element = getWebElement(driver, castParameter(locator, restValue));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return status;
        }

    }
    private void overrideGlobalTimeout(WebDriver driver, long timeInSecond){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator){
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement>element = getListElement(driver, locator);
        overrideGlobalTimeout(driver, LONG_TIMEOUT);

        if (element.size() == 0){
            return true;
        } else if (element.size() > 0 && !element.get(0).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementUndisplayed(WebDriver driver, String locator, String... restValue){
        overrideGlobalTimeout(driver, SHORT_TIMEOUT);
        List<WebElement>element = getListElement(driver, castParameter(locator, restValue));
        overrideGlobalTimeout(driver, LONG_TIMEOUT);
        if (element.size() == 0){
            return true;
        } else if (element.size() > 0 && !element.get(0).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restValue){
        return getWebElement(driver, castParameter(locator, restValue)).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator){
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElenment(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAndDrop(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),
                getWebElement(driver, targetLocator)).perform();
    }

    public void senKeyBoardToElement(WebDriver driver, String locator, Keys keys){
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys ).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }


    public boolean isExpectedTextInInnerText(WebDriver driver,String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void sleepInSecond(int timeout) {
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void hightlightElement(WebDriver driver,String locator) {
        WebElement element = getWebElement(driver,locator);
        String originalStyle = getElementDOMAttribute(driver,locator,"style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver,locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver,locator));
    }

    public void scrollToElementOnTop(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,locator));
    }

    public void scrollToElementOnTop(WebDriver driver,String locator, String... restValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver,castParameter(locator, restValue)));
    }

    public void scrollToElementOnDown(WebDriver driver,String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver,locator));
    }


    public String getAttributeInDOM(WebDriver driver,String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver,locator));
    }

    public String getElementValidationMessage(WebDriver driver,String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver,locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0"
                , getWebElement(driver,locator));
    }

    public void waitElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitElementVisible(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public void waitListElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitListElementVisible(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public void waitElementSelected(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitElementSelected(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restValue))));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }


    public WebElement waitElementClickable(WebDriver driver, WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator, String... restValue){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restValue))));
    }

    public void waitElementInvisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitElementInvisible(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public void waitElementInvisibleNotInDOM(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator){
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public boolean waitListElementInvisible(WebDriver driver, String locator, String... restValue){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, castParameter(locator, restValue))));
    }

    public void waitElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitElementPresence(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByLocator(castParameter(locator, restValue))));
    }

    public void waitListElementPresence(WebDriver driver, String locatorType){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public void waitListElementPresence(WebDriver driver, String locatorType, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(castParameter(locatorType, restValue))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames){
        String filePath = GlobalConstants.UPLOAD_PATH;
        String fullFileName = "";
        for (String file : fileNames){
            fullFileName = fullFileName + filePath + file + "\n";
        }
        getWebElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName.trim());
    }
//
//    public Boolean isLoadingIconDisappear(WebDriver driver) {
//        return waitListElementInvisible(driver, "//div[contains(@class,'oxd-loading-spinner')]");
//    }


//OpenCart
    public UserHomePO clickToLogoutLinkAtUserSite(WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);
        clickToElement(driver, BasePageUI.USER_MY_ACCOUNT_HEADER);

        waitElementClickable(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.USER_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(UserHomePO.class, driver);
}


    public AdminLoginPO clickToLogoutLinkAtAdminSite(WebDriver driver) {
        waitElementClickable(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        clickToElement(driver, BasePageUI.ADMIN_LOGOUT_LINK_ITEM);
        return PageGenerator.getPage(AdminLoginPO.class, driver);
    }

    public void openAdminSite(WebDriver driver, String adminUrl) {
        openPageUrl(driver, adminUrl);

    }

    public UserHomePO openUserSite(WebDriver driver, String userUrl) {
        openPageUrl(driver, userUrl);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    public UserHomePO openHomeLogo(WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_HOME_LOGO);
        clickToElement(driver, BasePageUI.USER_HOME_LOGO);
        return PageGenerator.getPage(UserHomePO.class, driver);
    }

    private String castParameter(String locator, String... values){
        return String.format(locator, (Object[]) values);
    }


    // OrangeHRM
    @Step("Waiting for Loading Spinner undisplay")
    public Boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

    @Step("Enter to {0} textbox by label with value {1}")
    public  void enterToTextboxByLabel(WebDriver driver, String textboxLabel, String valueToSenkey){
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        senKeyToElement(driver, BasePageUI.TEXTBOX_BY_LABEL, valueToSenkey, textboxLabel);
    }

    @Step("Enter to {0} textbox by name with value {1}")
    public  void enterToTextboxByName(WebDriver driver, String textboxNameAttribute, String valueToSenkey){
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        senKeyToElement(driver, BasePageUI.TEXTBOX_BY_NAME, valueToSenkey, textboxNameAttribute);
    }


    @Step("Click to {0} button by text")
    public void clickToButtonByText(WebDriver driver, String buttonText){
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT, buttonText);
    }

    public void clickToButtonByTextInMainTitle(WebDriver driver, String buttonText, String mainTitleName){
        waitElementClickable(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
        clickToElement(driver, BasePageUI.BUTTON_BY_TEXT_IN_MAIN_TITLE, mainTitleName, buttonText);
    }


    public String getTextboxValueByName(WebDriver driver, String textboxNameAttribute) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_NAME, textboxNameAttribute);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_NAME, "value", textboxNameAttribute);
    }

    public String getTextboxValueByLabel(WebDriver driver, String textboxLabel) {
        waitElementVisible(driver, BasePageUI.TEXTBOX_BY_LABEL, textboxLabel);
        return getElementDOMProperty(driver, BasePageUI.TEXTBOX_BY_LABEL, "value", textboxLabel);
    }

    @Step("Click to {0} module in Menu item")
    public void clickToModuleByTextInMenuItem(WebDriver driver, String moduleName) {
        waitElementClickable(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        clickToElement(driver,BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);

    }


    public boolean isModuleByTextInMenuItemDisplayed(WebDriver driver, String moduleName) {
        waitElementVisible(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementDisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public boolean isModuleByTextInMenuItemUndisplayed(WebDriver driver, String moduleName) {
        //waitElementInvisibleNotInDOM(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
        return isElementUndisplayed(driver, BasePageUI.MODULE_BY_TEXT_IN_MENU_ITEM, moduleName);
    }

    public void selectDropdownByLabel(WebDriver driver, String labelName, String valueToSelect) {
        waitElementClickable(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, labelName);
        selectItemInSelectableDropDown(driver, BasePageUI.PARENT_DROPDOWN_BY_LABEL, BasePageUI.CHILD_DROPDOWN_BY_LABEL, valueToSelect, labelName);
    }

    public boolean isToastMessageDisplayed(WebDriver driver, String toastMessage) {
        waitElementVisible(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
        return isElementDisplayed(driver, BasePageUI.TOAST_MESSAGE_BY_TEXT, toastMessage);
    }

    public void clickToRadioByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
        clickToElement(driver,BasePageUI.RADIO_BUTTON_BY_LABEL, labelName);
    }

    public void clickToCheckboxByLabel(WebDriver driver, String labelName) {
        waitElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, labelName);
        clickToElement(driver,BasePageUI.CHECKBOX_BY_LABEL, labelName);
    }

    public LoginPO clickLogoutOnTopMenu(WebDriver driver){
        waitElementClickable(driver, BasePageUI.USER_DROPDOWN);
        clickToElement(driver,BasePageUI.USER_DROPDOWN);
        waitElementClickable(driver, BasePageUI.LOGOUT_LINK);
        clickToElement(driver,BasePageUI.LOGOUT_LINK);
        return PageGenerator.getPage(LoginPO.class, driver);
    }

    private final int SHORT_TIMEOUT = GlobalConstants.SHORT_TIMEOUT;
    private final int LONG_TIMEOUT = GlobalConstants.LONG_TIMEOUT;


}

