package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class LoginPage extends TestHelperUtility {
    WebDriver driver;
    /*** Page Constructor ***/
    public LoginPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.LOGIN_SHEET_NAME);
    /*** Web Elements ***/
    private final String _loginUserName = "username";
    @FindBy(id=_loginUserName)
    private WebElement loginUserName;

    private final String _loginPassword = "password";
    @FindBy(id=_loginPassword)
    private WebElement loginPassword;

    private final String _loginButton = "//button[@class='btn btn-primary']";
    @FindBy(xpath=_loginButton)
    private WebElement loginButton;

    private final String _errorMessage = "//span[@class='help-block']//strong";
    @FindBy(xpath=_errorMessage)
    private WebElement errorMessage;

    private final String _rememberMeCheckbox = "//div[@class='checkbox']//input";
    @FindBy(xpath=_rememberMeCheckbox)
    private WebElement rememberMeCheckbox;

    private final String _forgotPassword = "//a[@class='btn btn-link']";
    @FindBy(xpath=_forgotPassword)
    private WebElement forgotPassword;
    /*** User action methods ***/
    public String getLoginPageActualTitle()
    {
        return page.getPageTitle(driver);
    }
    public void enterLoginUserName(String lUserNameToEnter)
    {
        page.enterText(loginUserName,lUserNameToEnter);
    }
    public void enterLoginPassword(String lPasswordToEnter)
    {
        page.enterText(loginPassword,lPasswordToEnter);
    }
    public HomePage clickOnLoginButton()
    {
        page.clickOnElement(loginButton);
        return new HomePage(driver);
    }
    public String getErrorMessage()
    {
        return page.getElementText(errorMessage);
    }
    public void clickOnCheckBox()
    {
        page.clickOnElement(rememberMeCheckbox);
    }
    public boolean getCheckboxStatus()
    {
        return page.isElementSelected(rememberMeCheckbox);
    }
    public ResetPasswordPage clickOnForgotPassword()
    {
        page.clickOnElement(forgotPassword);
        return new ResetPasswordPage(driver);
    }
    public List getLoginData() throws IOException {
        List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.LOGIN_SHEET_NAME);
        return excelList;
    }
    public String getActualLoginPageTitle()
    {
        return page.getPageTitle(driver);
    }
    public String getExpectedLoginPageTitle()
    {
        return excelList.get(0);
    }
}
