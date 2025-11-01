package employee;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Employee_01_CreateEmployee {
    private WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new FirefoxDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

    @Test
    public void Employee_01_NewEmployee(){

    }

    @Test
    public void Employee_02_EditEmployee(){

    }

    @Test
    public void Employee_03_MoveEmployee(){

    }

    @Test
    public void Employee_04_DeleteEmployee(){

    }

    @AfterClass
    public void afterClass(){
        driver.quit();

    }
}
