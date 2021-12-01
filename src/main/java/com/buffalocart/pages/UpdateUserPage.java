package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class UpdateUserPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public UpdateUserPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.UPDATE_USERS_SHEET_NAME);
    /*** Web Elements ***/
    private final String _updateButton = "submit_user_button";
    @FindBy(id=_updateButton)
    private WebElement updateButton;

    private final String _newEmail = "email";
    @FindBy(id=_newEmail)
    private WebElement newEmail;
    /*** User Action Methods ***/
    public String getEditUserSearch(){
        return excelList.get(1);
    }

    public String getExpectedEditUserPageTitle() {
        return excelList.get(0);
    }
    public String getActualEditUserPageTitle(){
        return page.getPageTitle(driver);
    }

    public String getNewDataToEdit() {
        return excelList.get(2);
    }
    public UsersPage clickOnUpdateButton() throws IOException {
        page.clickOnElement(updateButton);
        return new UsersPage(driver);
    }

    public void enterNewData(String newData) {
        page.enterText(newEmail,newData);
    }
    public void setValueEmail(){
        page.clearText(newEmail);
        page.enterText(newEmail,getNewDataToEdit());
    }
}
