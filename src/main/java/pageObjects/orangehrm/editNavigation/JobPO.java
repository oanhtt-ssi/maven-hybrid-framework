package pageObjects.orangehrm.editNavigation;

import org.openqa.selenium.WebDriver;

public class JobPO extends EditNavigatorPO {

    private WebDriver driver;
    public JobPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
