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

public class UpdateSalesCommissionAgentsPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public UpdateSalesCommissionAgentsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.UPDATE_SALES_COMMISSION_AGENTS_SHEET_NAME);
    /*** Web Elements ***/

    private final String _email = "email";
    @FindBy(id=_email)
    private WebElement email;

    private final String _saveButton = "//button[@class='btn btn-primary']";
    @FindBy(xpath = _saveButton)
    private WebElement saveButton;

    /*** User Action Methods ***/

    public String getNewData() {
        return excelList.get(0);
    }

    public void enterNewData(String newData) {
        //wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _email, wait.EXPLICIT_WAIT);
        page.clearText(email);
        page.enterText(email,newData);
    }

    public SalesCommissionAgentsPage clickOnUpdate() throws IOException {
        page.clickOnElement(saveButton);
        return new SalesCommissionAgentsPage(driver);
    }
}
