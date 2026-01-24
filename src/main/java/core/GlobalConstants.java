package core;

import pageObjects.PageGenerator;

public class GlobalConstants {

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String OS_NAME = System.getProperty("os.name");

    public static final String LOCAL_URL = "http://localhost:8080/orangehrm";
    public static final String LIVE_URL = "https://opensource-demo.orangehrmlive.com";

    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 15;

    public static final String UPLOAD_PATH = PROJECT_PATH + "/uploadFiles";
    public static final String DOWNLOAD_PATH = PROJECT_PATH + "/downloadFiles";

    public static final int RETRY_NUMBER = 3;

    public static final String BROWSER_LOG_PATH = PROJECT_PATH + "/browserLogs";
    public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + "/browserExtensions";

    public static final String REPORTNG_PATH = PROJECT_PATH + "/htmlReportNG";
    public static final String EXTENT_PATH = PROJECT_PATH + "/htmlExtent";
    public static final String ALLURE_PATH = PROJECT_PATH + "/htmlAllure";

    public static final String DATA_TEST_PATH = PROJECT_PATH + "/dataTest";
    public static final String ENVIRONMENT_CONFIG_PATH = PROJECT_PATH + "/environmentConfig";
}
