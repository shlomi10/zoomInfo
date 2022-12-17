package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;
import org.testng.annotations.*;
import pages.CalculatorPage;

/**
 * this class represents the base of all tests
 * this will be before each test in the testNG xml
 *
 * @author Shlomi
 */

public class BaseTest implements ITestListener {

    WebDriver driver;
    CalculatorPage mainAppPage;

    @Parameters({"browser"})
    @BeforeTest(alwaysRun = true)
    public void setup(String browser, ITestContext context) {

        // initialize and create the driver
        driver = new ChromeDriver();

        // maximize the browser window
        driver.manage().window().maximize();

        // load the main calculator page
        mainAppPage = new CalculatorPage(driver);

        context.setAttribute("driver", driver);

    }

    @AfterTest(alwaysRun = true)
    public void close() {
        driver.quit();

    }

}
