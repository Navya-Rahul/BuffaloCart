package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManagementPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public UserManagementPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*** Web Elements ***/
    private final String _userManagementButton = "//span[@class='title']";
    @FindBy(xpath = _userManagementButton)
    private WebElement userManagementButton;

    private final String _userManagementList = "//ul[@class='treeview-menu menu-open']//span";
    @FindBy(xpath = _userManagementList)
    private List<WebElement> userManagementList;
    /*** User Action Methods ***/
    public void clickOnUserManagementTab()
    {
        page.clickOnElement(userManagementButton);
    }
    public List getExpectedUserManagementTabValues() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.USER_MANAGEMENT_SHEET_NAME);
        return excelList;
    }
    public List getActualUserManagementTabValues()
    {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_userManagementList);
        List<String> list = new ArrayList<String>();
        for (int i = 0;i< userManagementList.size();i++)
        {
            list.add(userManagementList.get(i).getText());
        }
        return list;
    }
}
