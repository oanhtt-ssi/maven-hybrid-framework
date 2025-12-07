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

public class Level_02_BasePage_I {
    private WebDriver driver;
    private BasePage basePage;


    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        basePage = new BasePage();

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
        basePage.openPageUrl(driver,"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        basePage.senKeyToElement(driver,"//input[@name='username']","Admin");
        basePage.senKeyToElement(driver,"//input[@name='password']","admin123");
        basePage.clickToElement(driver,"//button[@type='submit']");


        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        Assert.assertTrue(basePage.isElementDisplayed(driver,"//h6[text()='Dashboard']"));

        basePage.clickToElement(driver,"//span[text()='PIM']/parent::a");
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        Assert.assertTrue(basePage.isElementDisplayed(driver,"//h6[text()='PIM']"));

        basePage.clickToElement(driver,"//a[text()='Add Employee']");
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));

        basePage.senKeyToElement(driver,"//input[@name='firstName']",firstName);
        basePage.senKeyToElement(driver,"//input[@name='lastName']",lastName);


        employeeID = basePage.getElementDOMProperty(driver,"//label[text()='Employee Id']/parent::div/following-sibling::div/input","value");
        basePage.clickToElement(driver,"//p[text()='Create Login Details']/following-sibling::div//span");
        basePage.sleepInSecond(2);

        basePage.senKeyToElement(driver,"//label[text()='Username']/parent::div/following-sibling::div/input",emailAddress);
        basePage.senKeyToElement(driver,"//label[text()='Password']/parent::div/following-sibling::div/input",password);
        basePage.senKeyToElement(driver,"//label[text()='Confirm Password']/parent::div/following-sibling::div/input",password);
        basePage.clickToElement(driver,"//button[@type='submit']");
        basePage.sleepInSecond(5);


        //Assert.assertTrue(isMessageSuccessDisplayed());
        basePage.isElementDisplayed(driver,"//p[text()='Successfully Saved']");
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));

        Assert.assertEquals(basePage.getElementDOMProperty(driver,"//input[@name='firstName']","value"),firstName);
        Assert.assertEquals(basePage.getElementDOMProperty(driver,"//input[@name='lastName']","value"),lastName);
        Assert.assertEquals(basePage.getElementDOMProperty(driver,"//label[text()='Employee Id']/parent::div/following-sibling::div/input","value"),employeeID);

        basePage.clickToElement(driver,"//a[text()='Immigration']");
        basePage.isElementDisplayed(driver,"//h6[text()='Assigned Immigration Records']");
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));

        basePage.clickToElement(driver,"//h6[text()='Assigned Immigration Records']/following-sibling::button");
        basePage.isElementDisplayed(driver,"//h6[text()='Add Immigration']");

        basePage.senKeyToElement(driver,"//label[text()='Number']/parent::div/following-sibling::div/input",number);
        basePage.senKeyToElement(driver,"textarea[placeholder='Type Comments here']",comment);
        basePage.clickToElement(driver,"button[type='submit']");

        basePage.isElementDisplayed(driver,"//p[text()='Successfully Saved']");
        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        basePage.isElementDisplayed(driver,"//h6[text()='Assigned Immigration Records']");
        basePage.clickToElement(driver,"//i[@class='oxd-icon bi-pencil-fill']/parent::button");


        Assert.assertTrue(basePage.isLoadingIconDisappear(driver));
        basePage.isElementDisplayed(driver,"//h6[text()='Edit Immigration']");


        Assert.assertEquals(basePage.getElementDOMProperty(driver,"//label[text()='Number']/parent::div/following-sibling::div/input","value"),number);

        Assert.assertEquals(basePage.getElementDOMProperty(driver,"//textarea[@placeholder='Type Comments here']","value"),comment);

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
