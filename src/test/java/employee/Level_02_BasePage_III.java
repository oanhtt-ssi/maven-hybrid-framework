package employee;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_III extends BasePage{
    private WebDriver driver;



    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();

        firstName = "Mariah";
        lastName = "Carey";
        fullName = firstName + " " + lastName;
        emailAddress= "mariah" + new Random().nextInt(999) + "@hotmail.com";
        password = "Test@123";
        number = new Random().nextInt(999) + "-" + new Random().nextInt(999) + "-" + new Random().nextInt(999);
        comment = "This is generated data\nof people";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void Employee_01_NewEmployee(){
        openPageUrl(driver,"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        senKeyToElement(driver,"//input[@name='username']","Admin");
        senKeyToElement(driver,"//input[@name='password']","admin123");
        clickToElement(driver,"//button[@type='submit']");


        Assert.assertTrue(isLoadingIconDisappear(driver));
        Assert.assertTrue(isElementDisplayed(driver,"//h6[text()='Dashboard']"));

        clickToElement(driver,"//span[text()='PIM']/parent::a");
        Assert.assertTrue(isLoadingIconDisappear(driver));
        Assert.assertTrue(isElementDisplayed(driver,"//h6[text()='PIM']"));

        clickToElement(driver,"//a[text()='Add Employee']");
        Assert.assertTrue(isLoadingIconDisappear(driver));

        senKeyToElement(driver,"//input[@name='firstName']",firstName);
        senKeyToElement(driver,"//input[@name='lastName']",lastName);


        employeeID = getElementDOMProperty(driver,"//label[text()='Employee Id']/parent::div/following-sibling::div/input","value");
        clickToElement(driver,"//p[text()='Create Login Details']/following-sibling::div//span");
        sleepInSecond(2);

        senKeyToElement(driver,"//label[text()='Username']/parent::div/following-sibling::div/input",emailAddress);
        senKeyToElement(driver,"//label[text()='Password']/parent::div/following-sibling::div/input",password);
        senKeyToElement(driver,"//label[text()='Confirm Password']/parent::div/following-sibling::div/input",password);
        clickToElement(driver,"//button[@type='submit']");
        sleepInSecond(5);


        //Assert.assertTrue(isMessageSuccessDisplayed());
        isElementDisplayed(driver,"//p[text()='Successfully Saved']");
        Assert.assertTrue(isLoadingIconDisappear(driver));
        Assert.assertTrue(isLoadingIconDisappear(driver));

        Assert.assertEquals(getElementDOMProperty(driver,"//input[@name='firstName']","value"),firstName);
        Assert.assertEquals(getElementDOMProperty(driver,"//input[@name='lastName']","value"),lastName);
        Assert.assertEquals(getElementDOMProperty(driver,"//label[text()='Employee Id']/parent::div/following-sibling::div/input","value"),employeeID);

        clickToElement(driver,"//a[text()='Immigration']");
        isElementDisplayed(driver,"//h6[text()='Assigned Immigration Records']");
        Assert.assertTrue(isLoadingIconDisappear(driver));

        clickToElement(driver,"//h6[text()='Assigned Immigration Records']/following-sibling::button");
        isElementDisplayed(driver,"//h6[text()='Add Immigration']");

        senKeyToElement(driver,"//label[text()='Number']/parent::div/following-sibling::div/input",number);
        senKeyToElement(driver,"textarea[placeholder='Type Comments here']",comment);
        clickToElement(driver,"button[type='submit']");

        isElementDisplayed(driver,"//p[text()='Successfully Saved']");
        Assert.assertTrue(isLoadingIconDisappear(driver));
        isElementDisplayed(driver,"//h6[text()='Assigned Immigration Records']");
        clickToElement(driver,"//i[@class='oxd-icon bi-pencil-fill']/parent::button");


        Assert.assertTrue(isLoadingIconDisappear(driver));
        isElementDisplayed(driver,"//h6[text()='Edit Immigration']");


        Assert.assertEquals(getElementDOMProperty(driver,"//label[text()='Number']/parent::div/following-sibling::div/input","value"),number);

        Assert.assertEquals(getElementDOMProperty(driver,"//textarea[@placeholder='Type Comments here']","value"),comment);

    }

    private Boolean isMessageSuccessDisplayed() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .invisibilityOfAllElements(driver.findElement(By.xpath("//p[text()='Successfully Saved']"))));
    }



    @AfterClass
    public void afterClass(){
        driver.quit();

    }
    String firstName, lastName, fullName, emailAddress, password, employeeID, number, comment;

}
