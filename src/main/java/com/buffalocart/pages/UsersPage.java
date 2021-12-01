package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TableUtility;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UsersPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public UsersPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.USERS_SHEET_NAME);
    /*** Web Elements ***/
    private final String _user = "//ul[@class='treeview-menu menu-open']//i[@class='fa fa-user']";
    @FindBy(xpath=_user)
    private WebElement user;

    private final String _searchBox = "//input[@class='form-control input-sm']";
    @FindBy(xpath=_searchBox)
    private WebElement searchBox;

    private final String _searchResult = "//table[@id='users_table']//tr/td[@class='sorting_1']";
    @FindBy(xpath=_searchResult)
    private WebElement searchResult;

    private final String _searchMessage = "//td[@class='dataTables_empty']";
    @FindBy(xpath=_searchMessage)
    private WebElement searchMessage;

    private final String _addUserButton = "//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath=_addUserButton)
    private WebElement addUserButton;

    private final String _rElement = "//table[@id='users_table']//tbody//tr";
    @FindBy(xpath = _rElement)
    private List<WebElement> rowElement;

    private final String _cElement = "//table[@id='users_table']//tbody//tr//td";
    @FindBy(xpath = _cElement)
    private List<WebElement> colElement;

    private final String _editButton = "//a[@class='btn btn-xs btn-primary']";
    @FindBy(xpath=_editButton)
    private WebElement editButton;

    /*** User Action Methods ***/
    public UsersPage clickOnUserMenu() throws IOException {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_user);
        page.clickOnElement(user);
        return new UsersPage(driver);
    }
    public String getActualUsersPageTitle()
    {
        return page.getPageTitle(driver);
    }
    public String getExpectedUsersPageTitle() throws IOException {
        return excelList.get(0);
    }
    public String getExpectedSearchData() {
        return excelList.get(1);
    }
    public void enterDataOnSearchBox(String dataToEnter)
    {
        page.enterText(searchBox,dataToEnter);
    }
    public String getActualSearchData()
    {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _searchResult);
        List<WebElement> usersWebElementList = page.getWebElementList(driver, _searchResult);
        String actualUserValue = page.getElementText(usersWebElementList.get(0));
        if (actualUserValue != " ") {
            System.out.println("True");
            return actualUserValue;
        } else {
            return " ";
        }
    }
    public String getInvalidSearchData() {
        return excelList.get(2);
    }
    public String getExpectedMessage() {
        return excelList.get(3);
    }
    public String getActualMessage() {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _searchMessage);
        return page.getElementText(searchMessage);
    }
    public AddUsersPage clickOnAddUsersButton() throws IOException {
        page.clickOnElement(addUserButton);
        return new AddUsersPage(driver);
    }
    public List<ArrayList<String>> getTableData() {
        return TableUtility.getGridData(rowElement, colElement);

    }

    public boolean getTableContainsData(List<ArrayList<String>> tableData, String expectedUserName) {
        boolean status = false;
        for(int i=0;i<tableData.size();i++)
            if(tableData.get(i).contains(expectedUserName)) {
                status = true;
            }
        return status;
    }
}
