package register;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;
import utilities.EnvironmentConfig;

public class Register extends BaseTest {
    private WebDriver driver;
    private EnvironmentConfig environment;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
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

        System.out.println(environment.appUrl());
    }

    @Test
    public void TC01_Register_Failed_Empty_Data_Tt() {
        registerPage = homePage.openRegisterPage();
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getFirstNameErrorMessageText(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMessageText(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(), "Password is required.");
    }

    @Test
    public void TC02_Register_Failed_Invalid_Email() {
        registerPage.enterFirstNameTextbox(firstName);
        registerPage.enterLastNameTextbox(lastName);
        registerPage.enterEmailTextbox("ngocmai.com");
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(password);
        registerPage.clickRegisterButton();

        Assert.assertEquals(registerPage.getEmailErrorMessageText(), "Please enter a valid email address.");
    }

    @Test
    public void TC03_Register_Failed_Invalid_Password() {
        registerPage.enterFirstNameTextbox(firstName);
        registerPage.enterLastNameTextbox(lastName);
        registerPage.enterEmailTextbox(emailAddress);
        registerPage.enterPasswordTextbox("123");
        registerPage.enterConfirmPasswordTextbox("123");
        registerPage.clickRegisterButton();

        Assert.assertEquals(registerPage.getPasswordErrorMessageText(),"<p>Password must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void TC04_Register_Failed_Invalid_Confirm_Password() {
        registerPage.enterFirstNameTextbox(firstName);
        registerPage.enterLastNameTextbox(lastName);
        registerPage.enterEmailTextbox(emailAddress);
        registerPage.enterPasswordTextbox("123456");
        registerPage.enterConfirmPasswordTextbox("123");
        registerPage.clickRegisterButton();

        Assert.assertEquals(registerPage.getConfirmPasswordErrorMessageText(),"The password and confirmation password do not match.");
    }

    @Test
    public void TC05_Register_Successful() {
        registerPage.enterFirstNameTextbox(firstName);
        registerPage.enterLastNameTextbox(lastName);
        registerPage.enterEmailTextbox(emailAddress);
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(password);
        registerPage.clickRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessfulMessageText(), "Your registration completed");
    }

    @AfterClass
    public void AfterClass() {
        quitBrowser();
    }

}
