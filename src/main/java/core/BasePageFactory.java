package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {
    public static BasePageFactory getBasePage(){
        return new BasePageFactory();
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


    public void clickToElement(WebDriver driver, WebElement element){

        element.click();
    }

    public void senKeyToElement(WebDriver driver, WebElement element, String keyToSend){
        element.clear();
        element.sendKeys(keyToSend);
    }



    public String getElementDOMAttribute(WebDriver driver, WebElement element, String attributeName){
        return element.getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, WebElement element, String propertyName){
        return element.getDomProperty(propertyName);
    }


    public String getElementText(WebDriver driver, WebElement element){
        return element.getText();
    }



    public boolean isElementDisplayed(WebDriver driver, WebElement element){
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, WebElement element){
        return element.isSelected();
    }



    public void waitElementVisible(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public void waitListElementVisible(WebDriver driver, List<WebElement> elements){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitElementSelected(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(element));
    }

    public void waitElementClickable(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitElementInvisible(WebDriver driver, WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInvisible(WebDriver driver, List<WebElement> elements){
       return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(elements));
    }


    public Boolean isLoadingIconDisappear(WebDriver driver, List<WebElement> elements) {
        return waitListElementInvisible(driver, elements);
    }



    private final int SHORT_TIMEOUT = 10;
    private final int LONG_TIMEOUT = 30;

}

