package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TableUtility;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RolesPage extends TestHelperUtility {
    WebDriver driver;
    boolean values;
    /*** Class Constructor ***/
    public RolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.ROLES_SHEET_NAME);
    /*** Web Elements ***/

    private final String _addRole = "//a[@class='btn btn-block btn-primary']";
    @FindBy(xpath=_addRole)
    private WebElement addRole;

    private final String _searchBox = "//input[@class='form-control input-sm']";
    @FindBy(xpath=_searchBox)
    private WebElement searchBox;

    private final String _searchResult = "roles_table";
    @FindBy(id=_searchResult)
    private WebElement searchResult;

    private final String _rowElement = "//table[@id='roles_table']//tbody//tr";
    @FindBy(xpath=_rowElement)
    private List<WebElement> rowElement;

    private final String _colElement = "//table[@id='roles_table']//tbody//tr//td";
    @FindBy(xpath=_colElement)
    private List<WebElement> colElement;

    private final String _editButton = "//td//a[@class='btn btn-xs btn-primary']";
    @FindBy(xpath=_editButton)
    private WebElement editButton;

    private final String _deleteButton = "//td//button[@class='btn btn-xs btn-danger delete_role_button']";
    @FindBy(xpath=_deleteButton)
    private WebElement deleteButton;
    /*** User Action Methods ***/

    public String getActualRolesPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedRolesPageTitle() {
        return excelList.get(0);
    }

    public AddRolesPage clickOnAddRolesButton() throws IOException {
        page.clickOnElement(addRole);
        return new AddRolesPage(driver);
    }

    public void enterTextOnSearchBox(String dataToEnter) {
        page.enterText(searchBox,dataToEnter);
    }

    public List<ArrayList<String>> getTableData() {
        return TableUtility.getGridData(rowElement, colElement);
    }

    public boolean getTableContainsData(List<ArrayList<String>> tableData, String roleToSearch) {
        boolean status = false;
        for (int i = 0; i < tableData.size(); i++)
            if (tableData.get(i).contains(roleToSearch)) {
                status = true;
            }
        return status;
    }

    public UpdateRolesPage clickOnEditButton(String roleToSearch) throws IOException {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _editButton, wait.IMPLICIT_WAIT);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (values == false) {
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);
                    if (values == false) {

                        String tData = data.getText();

                        if (tData.contains(roleToSearch)) {
                            editButton = driver.findElement(
                                    By.xpath("//table[@id='roles_table']//tbody//tr[" + (i + 1) + "]//td//a"));
                            page.clickOnElement(editButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        }

        return new UpdateRolesPage(driver);
    }

    public String getEditRoleSearch() {
        return excelList.get(1);
    }

    public String getRoleToBeDeleted() {
        return excelList.get(2);
    }

    public DeleteRolesPage clickOnDeleteButton(String roleToDelete) throws IOException {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _deleteButton, wait.IMPLICIT_WAIT);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (values == false)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (values == false) {
                        String tData = data.getText();
                        if (tData.contains(roleToDelete)) {
                            deleteButton = driver.findElement(
                                    By.xpath(("//table[@id='roles_table']//tbody//tr[" + (i + 1) + "]//td//button")));
                            page.clickOnElement(deleteButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new DeleteRolesPage(driver);
    }
}
