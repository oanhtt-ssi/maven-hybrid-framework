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

public class Level_13_Upload_File extends BaseTest {



    @Parameters({"url","browser"})
    @BeforeClass
    public void beforeClass(String url, String browserName){
         driver = getBrowserDriver(browserName, url);
         homePage = PageGenerator.getPage(HomePO.class, driver);
    }


    @Test
    public void Upload_01_Single(){

        homePage.uploadMultipleFiles(driver,mountainFile );
        homePage.uploadMultipleFiles(driver,riverFile );
        homePage.uploadMultipleFiles(driver,treeFile );

        Assert.assertTrue(homePage.isFileLoadedSuccess(mountainFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(riverFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(treeFile));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(mountainFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(riverFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(treeFile));



    }


    @Test
    public void Upload_02_Multiple(){
        homePage.refreshCurrentPage(driver);

        homePage.uploadMultipleFiles(driver,mountainFile, riverFile, treeFile );


        Assert.assertTrue(homePage.isFileLoadedSuccess(mountainFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(riverFile));
        Assert.assertTrue(homePage.isFileLoadedSuccess(treeFile));

        homePage.clickStartUpload();

        Assert.assertTrue(homePage.isFileUploadedSuccess(mountainFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(riverFile));
        Assert.assertTrue(homePage.isFileUploadedSuccess(treeFile));



    }


    @AfterClass
    public void afterClass(){
        closeBrowser(driver);
    }
    private WebDriver driver;
    private HomePO homePage;
    String mountainFile = "Mountain.png";
    String riverFile = "River.png";
    String treeFile = "Tree.png";
}
