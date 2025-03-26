package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    private WebDriver driver;
    private final String REGISTER_BTN = "css=a[class='ico-register']";
    private final String LOGIN_BTN = "css=a[class='ico-login']";
    private final String LOGOUT_BTN = "css=a[class='ico-logout']";

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open Register Page")
    public RegisterPageObject openRegisterPage() {
        waitForElementClickable(driver, REGISTER_BTN);
        clickElement(driver, REGISTER_BTN);
        return PageGeneratorManager.openRegisterPage(driver);
    }

    @Step("Open Login Page")
    public LoginPageObject openLoginPage() {
        waitForElementClickable(driver, LOGIN_BTN);
        clickElement(driver, LOGIN_BTN);
        return PageGeneratorManager.openLoginPage(driver);
    }

    @Step("Verify the Logout Button is displayed")
    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(driver, LOGOUT_BTN);
    }
}
