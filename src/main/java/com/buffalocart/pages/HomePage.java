package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomePage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*** Web Elements ***/
    private final String _userName = "//a[@class='dropdown-toggle']//span";
    @FindBy(xpath=_userName)
    private WebElement userName;

    private final String _endTourButton = "//button[@class='btn btn-default btn-sm']";
    @FindBy(xpath=_endTourButton)
    private WebElement endTourButton;

    private final String _logoutButton = "//div[@class='pull-right']//a";
    @FindBy(xpath=_logoutButton)
    private WebElement logoutButton;

    private final String _date = "//div[@class='m-8 pull-left mt-15 hidden-xs']//strong";
    @FindBy(xpath=_date)
    private WebElement date;

    /*** User Action Methods ***/
    public String getUserName()
    {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_userName,wait.EXPLICIT_WAIT);
        return page.getElementText(userName);
    }
    public void clickOnEndTourButton()
    {
        page.clickOnElement(endTourButton);
    }
    public void clickOnUserName()
    {
        page.clickOnElement(userName);
    }
    public LoginPage clickOnLogout()
    {
        page.clickOnElement(logoutButton);
        return new LoginPage(driver);
    }
    public String getHomePageActualTitle()
    {
        return page.getPageTitle(driver);
    }
    public String getHomePageExpectedTitle() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.HOME_SHEET_NAME);
        return excelList.get(0);
    }
    public String getExpectedDateInHomePage()
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String todayDate = dateFormat.format(date);
        return todayDate;
    }
    public String getActualDateInHomePage()
    {
        return page.getElementText(date);
    }
}
