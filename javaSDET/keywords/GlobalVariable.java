package keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GlobalVariable {
    //Biến non-static
    String addressName = "Ha Noi";

    //Biến tĩnh
    static String fullName = "Automation FC";

    //Biến final
    final String firstName = "John";

    //Biến static final
    static final String BROWSER_NAME = "Firefox";

    public static void main(String[] args){
        //new: khởi tạo 1 class lên - tạo ra 1 đối tượng/instance
        // 1 class có thể tạo ra n đối tượng
        GlobalVariable globalVariable = new GlobalVariable();
        System.out.println(globalVariable instanceof GlobalVariable);

        //Biến non-static: dùng thông qua 1 instance/đối tượng đại diện cho class - cho phép ghi đè
        System.out.println(globalVariable.addressName);
        globalVariable.addressName="Ho Chi Minh";

        //Biến static: trực tiếp từ class
        System.out.println(fullName);
        clickToButton();

        //Biến final: Không cho phép ghi đè
        //globalVariable.firstName="Mariah";

        //Biến static final: thuộc phạm vi class + không cho ghi đè

    }

    static void clickToButton(){

    }

    //Gọi hàm tuần tự - không đc phép gọi cùng thời điểm
    public synchronized WebDriver getBrowserDriver() {
            WebDriver driver = new FirefoxDriver();
            return driver;

    }
}
