package login;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;
import utilities.EnvironmentConfig;

public class Login extends BaseTest {
    private WebDriver driver;
    private EnvironmentConfig environment;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private String firstName = "Ngoc";
    private String lastName = "Mai";
    private String emailAddress = getRandomEmail("ngocmai");
    private String password = "123456";

    @Parameters({"browser","server"})
    @BeforeClass
    public void BeforeClass(@Optional("chrome") String browserName, @Optional("demo") String serverName) {
        ConfigFactory.setProperty("server", serverName);
        environment = ConfigFactory.create(EnvironmentConfig.class);
        driver = getBrowserName(browserName, environment.appUrl());
        homePage = PageGeneratorManager.openHomePage(driver);
    }

    @Test
    public void TC01_Login_Failed_Empty_Data() {
        loginPage = homePage.openLoginPage();
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter your email");
    }

    @Test
    public void TC02_Login_Failed_Email_Invalid() {
        loginPage.enterEmailTextbox("ngocmai.com");
        loginPage.enterPasswordTextbox("123456");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getEmailErrorMessage(), "Please enter a valid email address.");
    }

    @Test
    public void TC03_Login_Failed_Email_And_Password_Not_Match() {
        loginPage.enterEmailTextbox("ngocmai@gmail.com");
        loginPage.enterPasswordTextbox("123456");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
    }

    @Test
    public void TC04_Login_Successful() {
        registerPage = loginPage.openRegisterButton();
        registerPage.enterFirstNameTextbox(firstName);
        registerPage.enterLastNameTextbox(lastName);
        registerPage.enterEmailTextbox(emailAddress);
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(password);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getRegisterSuccessfulMessage(), "Your registration completed");
        homePage = registerPage.clickLogoutButton();
        loginPage = homePage.openLoginPage();
        loginPage = homePage.openLoginPage();
        loginPage.enterEmailTextbox(emailAddress);
        loginPage.enterPasswordTextbox(password);
        loginPage.clickLoginButton();
        homePage = PageGeneratorManager.openHomePage(driver);
        Assert.assertTrue(homePage.isLogoutButtonDisplayed());
    }

    @AfterClass(alwaysRun = true)
    public void AfterClass() {
        quitBrowser();
    }

}
