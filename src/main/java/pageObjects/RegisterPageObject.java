package pageObjects;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter Firstname in Textbox with value: {0}")
    public void enterFirstNameTextbox(String keysToSend) {
        clearKeysInElement(driver, RegisterPageUI.FIRSTNAME_TXT);
        sendKeysToElement(driver, RegisterPageUI.FIRSTNAME_TXT, keysToSend);
    }

    @Step("Enter Lastname in Textbox with value: {0}")
    public void enterLastNameTextbox(String keysToSend) {
        clearKeysInElement(driver, RegisterPageUI.LASTNAME_TXT);
        sendKeysToElement(driver, RegisterPageUI.LASTNAME_TXT, keysToSend);
    }

    @Step("Enter Email in Textbox with value: {0}")
    public void enterEmailTextbox(String keysToSend) {
        clearKeysInElement(driver, RegisterPageUI.EMAIL_TXT);
        sendKeysToElement(driver, RegisterPageUI.EMAIL_TXT, keysToSend);
    }

    @Step("Enter Password in Textbox with value: {0}")
    public void enterPasswordTextbox(String keysToSend) {
        clearKeysInElement(driver, RegisterPageUI.PASSWORD_TXT);
        sendKeysToElement(driver, RegisterPageUI.PASSWORD_TXT, keysToSend);
    }

    @Step("Enter Confirm Password in Textbox with value: {0}")
    public void enterConfirmPasswordTextbox(String keysToSend) {
        clearKeysInElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TXT);
        sendKeysToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TXT, keysToSend);
    }

    @Step("Click Register Button")
    public void clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Verify the Firstname Error Message is Displayed")
    public String getFirstNameErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
    }

    @Step("Verify the Lastname Error Message is Displayed")
    public String getLastNameErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
    }

    @Step("Verify the Email Error Message is Displayed")
    public String getEmailErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
    }

    @Step("Verify the Password Error Message is Displayed")
    public String getPasswordErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
    }

    @Step("Verify the Confirm Password Error Message is Displayed")
    public String getConfirmPasswordErrorMessageText() {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    @Step("Verify the Register Success Message is Displayed")
    public String getRegisterSuccessfulMessageText() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESSFUL_MGS);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESSFUL_MGS);
    }


}
