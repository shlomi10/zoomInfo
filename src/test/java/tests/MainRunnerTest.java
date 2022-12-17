package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;
import java.util.Properties;

/**
 * this class represents the test runner
 *
 * @author Shlomi
 */

public class MainRunnerTest extends BaseTest {

    private String siteURL;
	private final SoftAssert softAssert = new SoftAssert();


    @BeforeMethod
    public void beforeMethod() {
        try {
            // load properties
			Properties props = new Properties();

            String propFileName = "config.properties";
            // get the config properties file
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            props.load(inputStream);
            siteURL = props.getProperty("siteURL");
        } catch (Exception e) {
            System.out.println("There was problem load the properties file");
        }
    }

    @Parameters({"expectedExercise", "expectedResult",})
    @Test(priority = 1, description = "Addition test")
    public void additionTest(String expectedExercise, String expectedResult) {
        Assert.assertTrue(mainAppPage.getWebSite(siteURL), "site was not loaded");
        Assert.assertTrue(mainAppPage.validateSiteLoaded(), "The site is not the right one");
        softAssert.assertTrue(mainAppPage.clearCalculator(), "calculator wasn't cleared");
        mainAppPage.parseAndClick(expectedExercise);
        softAssert.assertEquals(mainAppPage.performCalculationAndGetResult(), expectedResult, "result of expected Exercise is not right");
        Assert.assertTrue(mainAppPage.openHistoryList(), "History list was not open");
        softAssert.assertEquals(mainAppPage.getHistoryResult(), expectedResult, "expected result is not the same as the history result");
        softAssert.assertEquals(mainAppPage.getHistorySequence(), expectedExercise, "expected exercise is not the same as the history exercise");
        softAssert.assertAll();
    }

    @Parameters({"expectedExercise", "expectedResult"})
    @Test(priority = 2, description = "Subtraction test")
    public void subtractionTest(String expectedExercise, String expectedResult) {
        Assert.assertTrue(mainAppPage.getWebSite(siteURL), "site was not loaded");
        Assert.assertTrue(mainAppPage.validateSiteLoaded(), "The site is not the right one");
        softAssert.assertTrue(mainAppPage.clearCalculator(), "calculator wasn't cleared");
        mainAppPage.parseAndClick(expectedExercise);
        softAssert.assertEquals(mainAppPage.performCalculationAndGetResult(), expectedResult, "result of expected Exercise is not right");
        Assert.assertTrue(mainAppPage.openHistoryList(), "History list was not open");
        softAssert.assertEquals(mainAppPage.getHistoryResult(), expectedResult, "expected result is not the same as the history result");
        softAssert.assertEquals(mainAppPage.getHistorySequence(), expectedExercise, "expected exercise is not the same as the history exercise");
        softAssert.assertAll();
    }

    @Parameters({"expectedExercise", "expectedResult"})
    @Test(priority = 3, description = "Multiplication test")
    public void multiplicationTest(String expectedExercise, String expectedResult) {
        Assert.assertTrue(mainAppPage.getWebSite(siteURL), "site was not loaded");
        Assert.assertTrue(mainAppPage.validateSiteLoaded(), "The site is not the right one");
        softAssert.assertTrue(mainAppPage.clearCalculator(), "calculator wasn't cleared");
        mainAppPage.parseAndClick(expectedExercise);
        softAssert.assertNotEquals(mainAppPage.performCalculationAndGetResult(), expectedResult, "result of expected Exercise is not right");
        Assert.assertTrue(mainAppPage.openHistoryList(), "History list was not open");
        softAssert.assertNotEquals(mainAppPage.getHistoryResult(), expectedResult, "expected result is not the same as the history result");
        softAssert.assertEquals(mainAppPage.getHistorySequence(), expectedExercise, "expected exercise is not the same as the history exercise");
        softAssert.assertAll();
    }

    @Parameters({"expectedExercise", "expectedResult"})
    @Test(priority = 4, description = "Sinus test")
    public void sinusTest(String expectedExercise, String expectedResult) {
        Assert.assertTrue(mainAppPage.getWebSite(siteURL), "site was not loaded");
        Assert.assertTrue(mainAppPage.validateSiteLoaded(), "The site is not the right one");
        softAssert.assertTrue(mainAppPage.clearCalculator(), "calculator wasn't cleared");
        mainAppPage.parseAndClick(expectedExercise);
        softAssert.assertEquals(mainAppPage.performCalculationAndGetResult(), expectedResult, "result of expected Exercise is not right");
        Assert.assertTrue(mainAppPage.openHistoryList(), "History list was not open");
        softAssert.assertEquals(mainAppPage.getHistoryResult(), expectedResult, "expected result is not the same as the history result");
        softAssert.assertEquals(mainAppPage.getHistorySequence(), expectedExercise, "expected exercise is not the same as the history exercise");
        softAssert.assertAll();
    }


}
