package register;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import utilities.EnvironmentConfig;
import utilities.ExcelConfig;

public class Register extends BaseTest {
    private WebDriver driver;
    private EnvironmentConfig environment;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private ExcelConfig excelConfig;

    @Parameters({"browser", "server"})
    @BeforeClass
    public void BeforeClass(@Optional("chrome") String browserName, @Optional("demo") String serverName) {
        ConfigFactory.setProperty("server", serverName);
        environment = ConfigFactory.create(EnvironmentConfig.class);
        driver = getBrowserName(browserName, environment.appUrl());
        homePage = PageGeneratorManager.openHomePage(driver);
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("RegisterData");
    }

    @Test
    public void TC01_Register_Failed_Empty_Data() {
        registerPage = homePage.openRegisterPage();
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMessage(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessage(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "Password is required.");
    }

    @Test
    public void TC02_Register_Failed_Invalid_Email() {
        registerPage.registerAccount(excelConfig, 1);
        Assert.assertEquals(registerPage.getEmailErrorMessage(), "Please enter a valid email address.");
    }

    @Test
    public void TC03_Register_Failed_Invalid_Password() {
        registerPage.registerAccount(excelConfig, 2);
        Assert.assertEquals(registerPage.getPasswordErrorMessage(), "<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void TC04_Register_Failed_Invalid_Confirm_Password() {
        registerPage.registerAccount(excelConfig, 3);
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessage(), "The password and confirmation password do not match.");
    }

    @Test
    public void TC05_Register_Successful() {
        registerPage.registerAccount(excelConfig, 4);
        Assert.assertEquals(registerPage.getRegisterSuccessfulMessage(), "Your registration completed");
    }

    @AfterClass
    public void AfterClass() {
        quitBrowser();
    }

}