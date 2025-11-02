package keywords;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.List;

public  class Topic_04_Statement {
    WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        BasePage basePage;
        WebDriver driver;
        String browserName = "Chrome";
        Thread.sleep(5000);

        //Điều kiện: if, if-else, switch-case

        if (browserName.equals("Chrome")) {
            driver = new ChromeDriver();
        }

        if (browserName.equals("Chrome")){
            driver = new ChromeDriver();
        }else if (browserName.equals("Firefox")){
            driver = new FirefoxDriver();
        }else {
            driver = new EdgeDriver();
        }

        switch (browserName){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new SafariDriver();
                break;
        }

        //Vòng lặp: for while do-while
        int i = 10;
        for (int j =0; j<i; j++){
            System.out.println(j);
        }

        List<WebElement> elements = driver.findElements(By.cssSelector(""));

        //for-each
        for (WebElement element:elements){
            element.getText();
        }

        //for-classic
        for (int x=0; x< elements.size(); x++){
            if (elements.get(4).getText().equals("Dev")){
                elements.get(x).click();
            }
        }

        //while
        int y=0;
        while (y<i){
            System.out.println(y);
            y++;
        }

        //do-while
        int z=0;
        do {
            System.out.println(z);
        } while (z<i);

    }

    public boolean isElementDisplayed(){
        try {
            return driver.findElement(By.cssSelector("")).isDisplayed();
        } catch (Exception e) {
           throw  new RuntimeException(e);
        } finally {

        }
    }
}
