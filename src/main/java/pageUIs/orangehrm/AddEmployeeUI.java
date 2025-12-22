package pageUIs.orangehrm;

public class AddEmployeeUI {
    public static final String FIRSTNAME_TEXTBOX = "//input[@name='firstName']";
    public static final String LASTNAME_TEXTBOX = "//input[@name='lastName']";
    public static final String EMPLOYEE_ID_TEXTBOX = "//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String CREATE_LOGIN_DETAIL_CHECKBOX = "//p[text()='Create Login Details']/following-sibling::div//span";
    public static final String USER_TEXTBOX = "//label[text()='Username']/parent::div/following-sibling::div/input";
    public static final String PASSWORD_TEXTBOX = "//label[text()='Password']/parent::div/following-sibling::div/input";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "//label[text()='Confirm Password']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON = "//button[@type='submit']";
    public static final String SUCCESSFULL_SAVE_MESSAGE = "//p[text()='Successfully Saved']";
}
