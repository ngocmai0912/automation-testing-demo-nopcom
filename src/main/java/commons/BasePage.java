package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasePage {

    protected void openPageUrl(WebDriver driver, String pageURL) {
        driver.get(pageURL);
    }

    protected String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    protected String getCurrentPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    protected String getPageSourceCode(WebDriver driver) {
        return driver.getPageSource();
    }

    protected void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    protected void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    protected void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    protected Alert waitForAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert(WebDriver driver) {
        waitForAlertPresence(driver).accept();
    }

    protected void cancelAlert(WebDriver driver) {
        waitForAlertPresence(driver).dismiss();
    }

    protected String getTextInAlert(WebDriver driver) {
        return waitForAlertPresence(driver).getText();
    }

    protected void sendKeysToAlert(WebDriver driver, String keysToSend) {
        waitForAlertPresence(driver).sendKeys(keysToSend);
    }

    protected void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> pageIDs = driver.getWindowHandles();
        for (String id : pageIDs) {
            driver.switchTo().window(id);
            if (driver.getTitle().equals(expectedTitle)) {
                break;
            }
        }
    }

    protected void closeAllWindowsWithoutParentID(WebDriver driver) {
        String currentPageID = driver.getWindowHandle();
        Set<String> pageIDs = driver.getWindowHandles();
        for (String id : pageIDs) {
            if (!id.equals(currentPageID)) {
                driver.switchTo().window(id);
                sleepInSeconds(2);
                driver.close();
            }
        }
        driver.switchTo().window(currentPageID);
    }

    protected void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected By getByLocator(String locator) {
        By by;
        if (locator.startsWith("xpath") || locator.startsWith("Xpath") || locator.startsWith("XPATH")) {
            by = By.xpath(locator.substring(6));
        } else if (locator.startsWith("css") || locator.startsWith("Css") || locator.startsWith("CSS")) {
            by = By.cssSelector(locator.substring(4));
        } else if (locator.startsWith("id") || locator.startsWith("ID")) {
            by = By.id(locator.substring(3));
        } else if (locator.startsWith("name") || locator.startsWith("NAME")) {
            by = By.name(locator.substring(5));
        } else if (locator.startsWith("class") || locator.startsWith("CLASS")) {
            by = By.className(locator.substring(6));
        } else if (locator.startsWith("tagname") || locator.startsWith("TAGNAME")) {
            by = By.tagName(locator.substring(8));
        } else {
            throw new RuntimeException("Locator type is not valid.");
        }
        return by;
    }

    protected WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListWebElements (WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected void clickElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    protected void sendKeysToElement (WebDriver driver, String locator, String keysToSend) {
        getWebElement(driver, locator).clear();
        getWebElement(driver, locator).sendKeys(keysToSend);
    }
    
    protected void clearKeysInElement (WebDriver driver, String locator) {
        getWebElement(driver, locator).clear();
    }

    protected void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(itemValue);
    }

    protected String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    protected boolean isDefaultDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    protected void selectItemInDropdown(WebDriver driver, String parentLocator, String childLocator, String itemValue) {
        getWebElement(driver, parentLocator).click();
        List<WebElement> listItems = new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(getByLocator(childLocator)));
        for (WebElement item : listItems) {
            if (item.getText().equals(itemValue)) {
                item.click();
                break;
            }
        }
    }

    protected String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getAttribute(attributeName);
    }

    protected String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    protected String convertRGBAToHexaColor(WebDriver driver, String locator) {
        return Color.fromString(getElementCssValue(driver, locator, "background-color")).asHex();
    }

    protected int getListWebElementsSize(WebDriver driver, String locator) {
        return getListWebElements(driver, locator).size();
    }

    protected void checkElement(WebDriver driver, String locator) {
        if (!getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    protected void uncheckElement(WebDriver driver, String locator) {
        if (getWebElement(driver, locator).isSelected()) {
            getWebElement(driver, locator).click();
        }
    }

    protected boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    protected boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    protected boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    protected void switchToIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    protected void waitForIframePresenceAndSwitchTo(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locator)));
    }

    protected void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    protected void hoverOverElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    protected void doubleClickElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    protected void rightClickElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    protected void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator),
                getWebElement(driver, targetLocator)).perform();
    }

    protected void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
    }

    protected void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", getWebElement(driver, locator));
    }

    protected void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", getWebElement(driver, locator));
    }

    protected void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void removeDOMAttribute(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('\" + attributeRemove + \"');", getWebElement(driver, locator));
    }

    protected String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    protected String getRandomEmail(String keysToSend) {
        return keysToSend + new Random().nextInt(999) + "@yopmail.com";
    }

    protected String getRandomNumberInEmail() {
        return new Random().nextInt(999) + "@yopmail.com";
    }

    protected void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.visibilityOf(getWebElement(driver, locator)));
    }

    protected void waitForListElementsVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.visibilityOfAllElements(getListWebElements(driver, locator)));
    }

    protected void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.invisibilityOf(getWebElement(driver, locator)));
    }

    protected void waitForListElementsInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locator)));
    }

    protected void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(30)).
                until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locator)));
    }
}
