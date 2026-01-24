package pageUIs.orangehrm;

public class AddEmployeeUI {
    public static final String FIRSTNAME_TEXTBOX = "name=firstName";
    public static final String LASTNAME_TEXTBOX = "name=lastName";
    public static final String EMPLOYEE_ID_TEXTBOX = "xpath=//label[text()='Employee Id']/parent::div/following-sibling::div/input";
    public static final String CREATE_LOGIN_DETAIL_CHECKBOX = "xpath=//p[text()='Create Login Details']/following-sibling::div//span";
    public static final String USER_TEXTBOX = "xpath=//label[text()='Username']/parent::div/following-sibling::div/input";
    public static final String PASSWORD_TEXTBOX = "xpath=//label[text()='Password']/parent::div/following-sibling::div/input";
    public static final String CONFIRM_PASSWORD_TEXTBOX = "xpath=//label[text()='Confirm Password']/parent::div/following-sibling::div/input";
    public static final String SAVE_BUTTON = "xpath=//button[@type='submit']";
    public static final String SUCCESSFULL_SAVE_MESSAGE = "xpath=//p[text()='Successfully Saved']";
    public static final String SPINNER_ICON = "Css=div.oxd-loading-spinner";
}
