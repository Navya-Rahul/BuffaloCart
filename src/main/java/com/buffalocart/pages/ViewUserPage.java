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

public class ViewUserPage extends TestHelperUtility {
    WebDriver driver;

    /*** Class Constructor ***/
    public ViewUserPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.VIEW_USERS_SHEET_NAME);
    /*** Web Elements ***/
    private final String _profileUserName = "//h3[@class='profile-username']";
    @FindBy(xpath=_profileUserName)
    private WebElement profileUserName;

    /*** User Action Methods ***/

    public String getActualProfileUserName()
    {
        return page.getElementText(profileUserName);
    }
    public String getExpectedProfileUserName()
    {
        return excelList.get(0);
    }
}
