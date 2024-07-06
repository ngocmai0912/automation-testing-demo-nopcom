package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter Email in Textbox with value: {0}")
    public void enterEmailTextbox(String keysToSend) {
        clearKeysInElement(driver, LoginPageUI.EMAIL_TXT);
        sendKeysToElement(driver, LoginPageUI.EMAIL_TXT, keysToSend);
    }

    @Step("Enter Password in Textbox with value: {0}")
    public void enterPasswordTextbox(String keysToSend) {
        clearKeysInElement(driver, LoginPageUI.PASSWORD_TXT);
        sendKeysToElement(driver, LoginPageUI.PASSWORD_TXT, keysToSend);
    }

    @Step("Click Login Button")
    public void clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BTN);
        clickElement(driver, LoginPageUI.LOGIN_BTN);
    }

    @Step("Verify the Email Error Message is Displayed")
    public String getEmailErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver, LoginPageUI.EMAIL_ERROR_MSG);
    }

    @Step("Verify the Summary Error Message is Displayed")
    public String getSummaryErrorMessage() {
        waitForElementVisible(driver, LoginPageUI.SUMMARY_ERROR_MSG);
        return getElementText(driver, LoginPageUI.SUMMARY_ERROR_MSG);
    }

    @Step("Click Register Button")
    public RegisterPageObject openRegisterButton() {
        waitForElementClickable(driver, LoginPageUI.REGISTER_BTN);
        clickElement(driver, LoginPageUI.REGISTER_BTN);
        return PageGeneratorManager.openRegisterPage(driver);
    }

}
