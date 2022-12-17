package utilities;

import java.io.File;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * this class represents the main functions for all pages
 *
 * @author Shlomi
 */

public abstract class BasePageFunctions {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // constructor
    public BasePageFunctions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // get the driver
    public WebDriver getDriver() {
        return this.driver;
    }

    // navigate to URL
    public Boolean navigateToURL(String URL) {
        try {
            getDriver().navigate().to(URL);
            return true;
        } catch (Exception e) {
            System.out.println("Site was not loaded");
            return false;
        }
    }

    // click on element
    public Boolean clickOnElement(By elem) {
        try {
            getDriver().findElement(elem).click();
            return true;
        } catch (Exception e) {
            System.out.println("Element " + elem + " was not clicked");
            return false;
        }
    }

    //wait for element to be clickable and click it
    public Boolean waitForElementToBeClickableAndClickIt(By elem) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(elem)).click();
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be clickable was not worked correct");
            return false;
        }
    }

    // wait for element to be visible
    public Boolean waitForElementToBeVisible(By elem) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elem));
            return true;
        } catch (Exception e) {
            System.out.println("Wait for element to be visible was not worked correct");
            return false;
        }
    }

    // get attribute from element
    public String getElementAttribute(By elem, String attribute) {
        return getDriver().findElement(elem).getAttribute(attribute);
    }

    // wait for element attribute to change
    public Boolean waitForElementAttributeToChange(By elem, String attributeName, String initialValue) {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(elem, attributeName, initialValue)));
            return true;
        } catch (Exception e) {
            System.out.println("value wasn't changed");
            return false;
        }
    }

    // take screenshot
    public Boolean takeScreenShot() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            //Convert web driver object to TakeScreenshot
            TakesScreenshot scrShot = ((TakesScreenshot) getDriver());
            //Call getScreenshotAs method to create image file
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            // take the tile and path
            String path = System.getProperty("user.dir") + File.separator + "Pictures" + File.separator + timestamp.getTime() + ".png";
            //Move image file to new destination
            File DestFile = new File(path);
            //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);
            return true;
        } catch (Exception e) {
            System.out.println("No screenshot was made");
            return false;
        }
    }
}
