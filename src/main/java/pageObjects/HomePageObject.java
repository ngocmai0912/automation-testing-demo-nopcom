package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open Register Page")
    public RegisterPageObject openRegisterPage() {
        waitForElementClickable(driver, HomePageUI.REGISTER_BTN);
        clickElement(driver, HomePageUI.REGISTER_BTN);
        return PageGeneratorManager.openRegisterPage(driver);
    }

    @Step("Open Login Page")
    public LoginPageObject openLoginPage() {
        waitForElementClickable(driver, HomePageUI.LOGIN_BTN);
        clickElement(driver, HomePageUI.LOGIN_BTN);
        return PageGeneratorManager.openLoginPage(driver);
    }

    @Step("Verify the Logout Button is displayed")
    public boolean isLogoutButtonDisplayed() {
        return isElementDisplayed(driver, HomePageUI.LOGOUT_BTN);
    }
}
