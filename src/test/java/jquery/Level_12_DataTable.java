package jquery;

import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.PageGenerator;
import pageObjects.jquery.HomePO;

import java.util.List;

public class Level_12_DataTable extends BaseTest {



    @Parameters({"url","browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
         driver = getBrowserDriver(browserName, url);
         homePage = PageGenerator.getPage(HomePO.class, driver);
    }

    @Test(enabled = false)
    public void Table_01_Paging(){
        homePage.openPageByNumber("10");
        Assert.assertTrue(homePage.isPageActiveByNumber("10"));

        homePage.openPageByNumber("3");
        Assert.assertTrue(homePage.isPageActiveByNumber("3"));

        homePage.openPageByNumber("13");
        Assert.assertTrue(homePage.isPageActiveByNumber("13"));

    }

    @Test(enabled = false)
    public void Table_02_Search(){
        homePage.enterToHeaderTextboxByName("Country", "Afghanistan");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isPageInfoDisplayed("384187", "Afghanistan", "407124", "791312"));
        homePage.refreshCurrentPage(driver);

        homePage.enterToHeaderTextboxByName("Females", "276880");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isPageInfoDisplayed("276880", "Angola", "276472", "553353"));
        homePage.refreshCurrentPage(driver);


        homePage.enterToHeaderTextboxByName("Males", "25266");
        homePage.sleepInSecond(3);
        Assert.assertTrue(homePage.isPageInfoDisplayed("24128", "Albania", "25266", "49397"));
        homePage.refreshCurrentPage(driver);

    }

    @Test(enabled = false)
    public void  Table_03_Action(){
        homePage.enterToHeaderTextboxByName("Country", "Uzbekistan");
        homePage.sleepInSecond(3);

        homePage.clickToActionByCountryName("Uzbekistan", "remove");

        homePage.refreshCurrentPage(driver);
    }

    @Test(enabled = false)
    public void Table_04_Index(){
        homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickLoadDataButton();

        homePage.enterToTextboxByColumnNameAndRowIndex("Company", "3", "Russia");
        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person", "3", "Vladimir Putin");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed", "3", "15");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country", "3", "Hong Kong");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?", "3");
        homePage.actionToRowByRowIndex("3", "Move Up");

        homePage.enterToTextboxByColumnNameAndRowIndex("Company", "6", "USA");
        homePage.enterToTextboxByColumnNameAndRowIndex("Contact Person", "6", "Donald Trump");
        homePage.enterToTextboxByColumnNameAndRowIndex("Order Placed", "6", "10");
        homePage.selectToDropdownByColumnNameAndRowIndex("Country", "6", "United States");
        homePage.checkToCheckboxByColumnNameAndRowIndex("NPO?", "6");
        homePage.actionToRowByRowIndex("6", "Insert");

    }

    @Test
    public void Table_05_Get_All_Value(){
        List<String> countryActualValue = homePage.getColumnAllValueByColumnName("Country");
        System.out.println(countryActualValue.size());

        List<String> femalesActualValue = homePage.getColumnAllValueByColumnName("Females");
    }

    @AfterClass
    public void afterClass(){
        closeBrowser(driver);
    }
    private WebDriver driver;
    private HomePO homePage;
}
