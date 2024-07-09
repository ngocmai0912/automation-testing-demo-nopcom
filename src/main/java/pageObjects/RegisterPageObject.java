package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;
import utilities.ExcelConfig;

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
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BTN);
        clickElement(driver, RegisterPageUI.REGISTER_BTN);
    }

    @Step("Register with Excel data row: {1}")
    public void registerAccount(ExcelConfig excelConfig, int rowNumber) {
        enterFirstNameTextbox(excelConfig.getCellData("firstName", rowNumber));
        enterLastNameTextbox(excelConfig.getCellData("lastName", rowNumber));
        enterEmailTextbox(getRandomEmail(excelConfig.getCellData("email", rowNumber)));
        enterPasswordTextbox(excelConfig.getCellData("password", rowNumber));
        enterConfirmPasswordTextbox(excelConfig.getCellData("confirmPassword", rowNumber));
        clickRegisterButton();
    }

    @Step("Verify the Firstname Error Message is Displayed")
    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.FIRSTNAME_ERROR_MSG);
    }

    @Step("Verify the Lastname Error Message is Displayed")
    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.LASTNAME_ERROR_MSG);
    }

    @Step("Verify the Email Error Message is Displayed")
    public String getEmailErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
    }

    @Step("Verify the Password Error Message is Displayed")
    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
    }

    @Step("Verify the Confirm Password Error Message is Displayed")
    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    @Step("Verify the Register Success Message is Displayed")
    public String getRegisterSuccessfulMessage() {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESSFUL_MGS);
        return getElementText(driver, RegisterPageUI.REGISTER_SUCCESSFUL_MGS);
    }

    @Step("Click Logout Button")
    public HomePageObject clickLogoutButton() {
        waitForElementClickable(driver, RegisterPageUI.LOGOUT_BTN);
        clickElement(driver, RegisterPageUI.LOGOUT_BTN);
        return PageGeneratorManager.openHomePage(driver);

    }


}
