package commons;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class PageGeneratorManager {
    public static HomePageObject openHomePage(WebDriver driver) {
        return new HomePageObject(driver);
    }

    public static RegisterPageObject openRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static LoginPageObject openLoginPage(WebDriver driver) {
        return new LoginPageObject();
    }

}
