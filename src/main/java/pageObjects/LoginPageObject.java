package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    private WebDriver driver;
    private static final String EMAIL_TXT = "css=input[id='Email']";
    private final String PASSWORD_TXT = "css=input[id='Password']";
    private final String LOGIN_BTN = "xpath=//button[text()='Log in']";
    private final String EMAIL_ERROR_MSG = "css=span[id='Email-error']";
    private final String SUMMARY_ERROR_MSG = "css=div[class='message-error validation-summary-errors']";
    private final String REGISTER_BTN = "css=a[class='ico-register']";

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter Email in Textbox with value: {0}")
    public void enterEmailTextbox(String keysToSend) {
        clearKeysInElement(driver, EMAIL_TXT);
        sendKeysToElement(driver, EMAIL_TXT, keysToSend);
    }

    @Step("Enter Password in Textbox with value: {0}")
    public void enterPasswordTextbox(String keysToSend) {
        clearKeysInElement(driver, PASSWORD_TXT);
        sendKeysToElement(driver, PASSWORD_TXT, keysToSend);
    }

    @Step("Click Login Button")
    public void clickLoginButton() {
        waitForElementClickable(driver, LOGIN_BTN);
        clickElement(driver, LOGIN_BTN);
    }

    @Step("Verify the Email Error Message is Displayed")
    public String getEmailErrorMessage() {
        waitForElementVisible(driver, EMAIL_ERROR_MSG);
        return getElementText(driver, EMAIL_ERROR_MSG);
    }

    @Step("Verify the Summary Error Message is Displayed")
    public String getSummaryErrorMessage() {
        waitForElementVisible(driver, SUMMARY_ERROR_MSG);
        return getElementText(driver, SUMMARY_ERROR_MSG);
    }

    @Step("Click Register Button")
    public RegisterPageObject openRegisterButton() {
        waitForElementClickable(driver, REGISTER_BTN);
        clickElement(driver, REGISTER_BTN);
        return PageGeneratorManager.openRegisterPage(driver);
    }

}
