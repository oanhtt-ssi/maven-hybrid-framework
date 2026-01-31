package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.PageGenerator;
import pageObjects.openCart.admin.AdminLoginPO;
import pageObjects.openCart.user.UserHomePO;
import pageUIs.BasePageUI;

import java.time.Duration;
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
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restValue){
        return getWebElement(driver, castParameter(locator, restValue)).isDisplayed();
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

    public void waitElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitElementClickable(WebDriver driver, String locator, String... restValue){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
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

    public Boolean isLoadingIconDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, "//div[contains(@class,'oxd-loading-spinner')]");
    }


    public Boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInvisible(driver, BasePageUI.SPINNER_ICON);
    }

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

    private final int SHORT_TIMEOUT = GlobalConstants.SHORT_TIMEOUT;
    private final int LONG_TIMEOUT = GlobalConstants.LONG_TIMEOUT;
}

