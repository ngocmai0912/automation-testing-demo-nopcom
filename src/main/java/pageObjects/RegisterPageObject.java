package pageObjects;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utilities.ExcelConfig;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;
    private final String FIRSTNAME_TXT = "css=input[id='FirstName']";
    private final String LASTNAME_TXT = "css=input[id='LastName']";
    private final String EMAIL_TXT = "css=input[id='Email']";
    private final String PASSWORD_TXT = "css=input[id='Password']";
    private final String CONFIRM_PASSWORD_TXT = "css=input[id='ConfirmPassword']";
    private final String REGISTER_BTN = "css=button[id='register-button']";
    private final String FIRSTNAME_ERROR_MSG = "css=span[id='FirstName-error']";
    private final String LASTNAME_ERROR_MSG = "css=span[id='LastName-error']";
    private final String EMAIL_ERROR_MSG = "css=span[id='Email-error']";
    private final String PASSWORD_ERROR_MSG = "css=span[data-valmsg-for='Password']";
    private final String CONFIRM_PASSWORD_ERROR_MSG = "css=span[id='ConfirmPassword-error']";
    private final String REGISTER_SUCCESSFUL_MGS = "css=div[class='result']";
    private final String LOGOUT_BTN = "css=a[class='ico-logout']";
    
    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Enter Firstname in Textbox with value: {0}")
    public void enterFirstNameTextbox(String keysToSend) {
        clearKeysInElement(driver, FIRSTNAME_TXT);
        sendKeysToElement(driver, FIRSTNAME_TXT, keysToSend);
    }

    @Step("Enter Lastname in Textbox with value: {0}")
    public void enterLastNameTextbox(String keysToSend) {
        clearKeysInElement(driver, LASTNAME_TXT);
        sendKeysToElement(driver, LASTNAME_TXT, keysToSend);
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

    @Step("Enter Confirm Password in Textbox with value: {0}")
    public void enterConfirmPasswordTextbox(String keysToSend) {
        clearKeysInElement(driver, CONFIRM_PASSWORD_TXT);
        sendKeysToElement(driver, CONFIRM_PASSWORD_TXT, keysToSend);
    }

    @Step("Click Register Button")
    public void clickRegisterButton() {
        waitForElementClickable(driver, REGISTER_BTN);
        clickElement(driver, REGISTER_BTN);
    }

    @Step("Register with Excel data row: {1}")
    public void registerAccount(ExcelConfig excelConfig, int rowNumber) {
        enterFirstNameTextbox(excelConfig.getCellData("firstName", rowNumber));
        enterLastNameTextbox(excelConfig.getCellData("lastName", rowNumber));
        enterEmailTextbox(excelConfig.getCellData("email", rowNumber) + getRandomNumberInEmail());
        enterPasswordTextbox(excelConfig.getCellData("password", rowNumber));
        enterConfirmPasswordTextbox(excelConfig.getCellData("confirmPassword", rowNumber));
        clickRegisterButton();
    }

    @Step("Verify the Firstname Error Message is Displayed")
    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, FIRSTNAME_ERROR_MSG);
        return getElementText(driver, FIRSTNAME_ERROR_MSG);
    }

    @Step("Verify the Lastname Error Message is Displayed")
    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, LASTNAME_ERROR_MSG);
        return getElementText(driver, LASTNAME_ERROR_MSG);
    }

    @Step("Verify the Email Error Message is Displayed")
    public String getEmailErrorMessage() {
        waitForElementVisible(driver, EMAIL_ERROR_MSG);
        return getElementText(driver, EMAIL_ERROR_MSG);
    }

    @Step("Verify the Password Error Message is Displayed")
    public String getPasswordErrorMessage() {
        waitForElementVisible(driver, PASSWORD_ERROR_MSG);
        return getElementText(driver, PASSWORD_ERROR_MSG);
    }

    @Step("Verify the Confirm Password Error Message is Displayed")
    public String getConfirmPasswordErrorMessage() {
        waitForElementVisible(driver, CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver, CONFIRM_PASSWORD_ERROR_MSG);
    }

    @Step("Verify the Register Success Message is Displayed")
    public String getRegisterSuccessfulMessage() {
        waitForElementVisible(driver, REGISTER_SUCCESSFUL_MGS);
        return getElementText(driver, REGISTER_SUCCESSFUL_MGS);
    }

    @Step("Click Logout Button")
    public HomePageObject clickLogoutButton() {
        waitForElementClickable(driver, LOGOUT_BTN);
        clickElement(driver, LOGOUT_BTN);
        return PageGeneratorManager.openHomePage(driver);

    }


}
