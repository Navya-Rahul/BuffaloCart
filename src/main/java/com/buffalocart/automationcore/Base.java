package com.buffalocart.automationcore;

import com.aventstack.extentreports.ExtentTest;
import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.EmailUtility;
import com.buffalocart.utilities.WaitUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {public WebDriver driver;
    FileInputStream file;
    public Properties prop;
    EmailUtility email;
    public static ExtentTest extentTest;
    public Base()
    {
        try {
            file = new FileInputStream(System.getProperty("user.dir")+ Constants.CONFIG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop = new Properties();
        try {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Deprecated
    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(WaitUtility.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browserName) {
        String url = prop.getProperty("url");
        testInitialize(browserName);
        driver.get(url);
    }
    @AfterSuite
    public void sendingEmail(){
        String dateName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        email = new EmailUtility();
        email.sendEmail(System.getProperty("user.dir")+"//TestReport//","ExtentReport_"+dateName+".html", prop.getProperty("toEmail"));

    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        String path = takeScreenshot(result);
        driver.close();
    }
    public String takeScreenshot(ITestResult result) throws IOException
    {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        if (ITestResult.FAILURE == result.getStatus())
        {
            TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
            File screenshot = takeScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File("./Screenshots/"+result.getName()+currentDate+".png"));
        }
        String path = System.getProperty("user.dir")+"./Screenshots/"+result.getName()+currentDate+".png";
        return path;
    }

}
