package reportManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * this class represent the initializing report
 */

public class ExtentManager {
    protected ExtentReports extent;
    protected ExtentTest test;
    protected ExtentSparkReporter spark;
    protected String reportDate;
    protected String filePath;
    protected String projectPath;


    public void init() {
        reportDate = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
        filePath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + reportDate;
        new File(filePath).mkdirs();
        projectPath = System.getProperty("user.dir");

        spark = new ExtentSparkReporter(filePath + "/report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);

        spark.config().setDocumentTitle("Automation report on calculator site");
        spark.config().setReportName("calculator Test");
        spark.config().setTheme(Theme.DARK);

    }

    public void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public String captureScreen(WebDriver driver) throws IOException {
        String picDate = new SimpleDateFormat("HH-mm-ss").format(new Date());
        String imagePath = filePath + "/screenshots-" + picDate + ".jpg";
        String absolutePath = projectPath + imagePath;

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, new File(imagePath));

        return absolutePath;
    }

}
