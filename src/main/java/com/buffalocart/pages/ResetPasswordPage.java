package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ResetPasswordPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public ResetPasswordPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    /*** Web Elements ***/

    private final String _recoverEmail = "email";
    @FindBy(id=_recoverEmail)
    private WebElement recoverEmail;

    private final String _resetButton = "//button[@class='btn btn-primary']";
    @FindBy(xpath=_resetButton)
    private WebElement resetButton;

    private final String _resetErrorMessage = "//span[@class='help-block']//strong";
    @FindBy(xpath=_resetErrorMessage)
    private WebElement resetErrorMessage;
    /*** User Action Methods ***/
    public void enterRecoverEmailToEnter(String recoverEmailToEnter)
    {
        page.enterText(recoverEmail,recoverEmailToEnter);
    }
    public void clickOnResetPasswordButton()
    {
        page.clickOnElement(resetButton);
    }
    public String getRecoverEmailToEnter() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.RESET_PASSWORD_SHEET_NAME);
        return excelList.get(0);
    }
    public String getActualErrorMessage()
    {
        return page.getElementText(resetErrorMessage);
    }
    public String getExpectedErrorMessage() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.RESET_PASSWORD_SHEET_NAME);
        return excelList.get(1);
    }
}
