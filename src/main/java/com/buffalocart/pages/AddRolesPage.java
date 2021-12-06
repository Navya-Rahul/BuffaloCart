package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class AddRolesPage extends TestHelperUtility {
    WebDriver driver;

    /*** Class Constructor ***/
    public AddRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.ADD_ROLES_SHEET_NAME);

    /*** Web Elements ***/
    private final String _newRole = "name";
    @FindBy(id=_newRole)
    private WebElement newRole;

    private final String _saveButton = "//button[@class='btn btn-primary pull-right']";
    @FindBy(xpath=_saveButton)
    private WebElement saveButton;

    /*** User Action Methods ***/
    public String getActualAddRolesPage() {
        return page.getPageTitle(driver);
    }
    public String getExpectedAddRolesPageTitle() {
        return excelList.get(0);
    }

    public String getRoleToEnter() {
        return excelList.get(2);
    }

    public void enterRole(String roleToEnter) {
        page.enterText(newRole,roleToEnter);
    }

    public RolesPage clickOnSaveButton() throws IOException {
        page.clickOnElement(saveButton);
        return new RolesPage(driver);
    }

    public String getExpectedRole() {
        return excelList.get(2);
    }
}
