package employee;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_03_Multiple_Browser extends BaseTest {
    private WebDriver driver;


    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName){

        driver = getBrowserDriver(browserName);
        firstName = "Mariah";
        lastName = "Carey";
        fullName = firstName + " " + lastName;
        emailAddress= "mariah" + new Random().nextInt(999) + "@hotmail.com";
        password = "Test@123";
        number = new Random().nextInt(999) + "-" + new Random().nextInt(999) + "-" + new Random().nextInt(999);
        comment = "This is generated data\nof people";


    }

    @Test
    public void Employee_01_NewEmployee() {
    }


    @AfterClass
    public void afterClass(){
        driver.quit();

    }
    String firstName, lastName, fullName, emailAddress, password, employeeID, number, comment;

}
