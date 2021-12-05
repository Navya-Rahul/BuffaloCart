package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateRolesPage extends TestHelperUtility {
    WebDriver driver;

    /*** Class Constructor ***/
    public UpdateRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.UPDATE_ROLES_SHEET_NAME);
    /*** Web Elements ***/
    private final String _selectUser ="//*[@id=\"role_form\"]/div[3]/div[2]/div/label/div/ins";
    @FindBy(xpath=_selectUser)
    private WebElement selectUser;

    private final String _updateButton ="//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath=_updateButton)
    private WebElement updateButton;

    private final String _selectUserStatus ="//*[@id=\"role_form\"]/div[3]/div[2]/div/label/div";
    @FindBy(xpath=_selectUserStatus)
    private WebElement selectUserStatus;
    /*** User Action Methods ***/

    public String getActualEditRolesPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedEditRolesPageTitle() {
        return excelList.get(0);
    }

    public boolean isSelectUserSelected()
    {
        return page.isElementSelected(selectUser);
    }

    public void clickOnSelectUser() {
        page.clickOnElement(selectUser);
    }

    public RolesPage clickOnUpdateButton() throws IOException {
        page.clickOnElement(updateButton);
        return new RolesPage(driver);
    }
    public String getAttributeName()
    {
        return excelList.get(1);
    }
    public String getActualSelectUser()
    {
        return page.getAttributeValue(selectUserStatus,getAttributeName());
    }

    public String getExpectedSelectUser() {
        return excelList.get(2);
    }
}
