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

public class DeleteSalesCommissionAgentsPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public DeleteSalesCommissionAgentsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.DELETE_SALES_COMMISSION_AGENTS_SHEET_NAME);
    /*** Web Elements ***/
    private final String _deleteButton="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _deleteButton)
    private WebElement deleteButton;
    public SalesCommissionAgentsPage clickOnOkButton() throws IOException {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _deleteButton);
        page.clickOnElement(deleteButton);
        return new SalesCommissionAgentsPage(driver);
    }
}
