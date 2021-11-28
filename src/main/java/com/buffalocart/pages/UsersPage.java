package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UsersPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public UsersPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*** Web Elements ***/
    private final String _user = "//ul[@class='treeview-menu menu-open']//i[@class='fa fa-user']";
    @FindBy(xpath=_user)
    private WebElement user;

    private final String _searchBox = "//input[@class='form-control input-sm']";
    @FindBy(xpath=_searchBox)
    private WebElement searchBox;

    /*** User Action Methods ***/
    public UsersPage clickOnUserMenu()
    {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_user);
        page.clickOnElement(user);
        return new UsersPage(driver);
    }
    public String getActualUsersPageTitle()
    {
        return page.getPageTitle(driver);
    }
    public String getExpectedUsersPageTitle() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.USERS_SHEET_NAME);
        return excelList.get(0);
    }
    public String getDataToEnter() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.USERS_SHEET_NAME);
        return excelList.get(1);
    }
    public void enterDataOnSearchBox(String dataToEnter)
    {
        page.enterText(searchBox,dataToEnter);
    }
}
