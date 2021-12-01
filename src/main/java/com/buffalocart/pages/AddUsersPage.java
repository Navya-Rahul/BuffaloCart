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

public class AddUsersPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public AddUsersPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH,Constants.ADD_USERS_SHEET_NAME);

    /*** Web Elements ***/
    private final String _addUserErrorMessage = "first_name-error";
    @FindBy(id=_addUserErrorMessage)
    private WebElement addUserErrorMessage;

    private final String _saveButton = "//button[@id='submit_user_button']";
    @FindBy(xpath=_saveButton)
    private WebElement saveButton;

    private final String _prefix = "surname";
    @FindBy(id=_prefix)
    private WebElement prefix;

    private final String _firstName = "first_name";
    @FindBy(id=_firstName)
    private WebElement firstName;

    private final String _lastName = "last_name";
    @FindBy(id=_lastName)
    private WebElement lastName;

    private final String _email = "email";
    @FindBy(id=_email)
    private WebElement email;

    private final String _role = "role";
    @FindBy(id=_role)
    private WebElement role;

    private final String _userName = "username";
    @FindBy(id=_userName)
    private WebElement userName;

    private final String _password = "password";
    @FindBy(id=_password)
    private WebElement password;

    private final String _confirmPassword = "confirm_password";
    @FindBy(id=_confirmPassword)
    private WebElement confirmPassword;

    private final String _toastMessage = "//div[@id='toast-container']";
    @FindBy(xpath = _toastMessage)
    private WebElement toastMessage;
    /*** User Action Methods ***/
    public String getExpectedAddUsersPageTitle() {
        return excelList.get(0);
    }
    public String getActualAddUsersPageTitle() {
        return page.getPageTitle(driver);
    }
    public UsersPage clickOnSaveButton() throws IOException {
        page.clickOnElement(saveButton);
        return new UsersPage(driver);
    }
    public String getActualErrorMessage() {
        return page.getElementText(addUserErrorMessage);
    }
    public String getExpectedErrorMessage() {
        return excelList.get(4);
    }
    public String getPrefix()
    {
        return excelList.get(1);
    }
    public String getFirstName()
    {
        return excelList.get(2);
    }
    public String getLastName()
    {
        return excelList.get(3);
    }
    public String getExpectedUserName()
    {
        return excelList.get(4);
    }
    public String getPassword()
    {
        return excelList.get(5);
    }
    public String getConfirmPassword()
    {
        return excelList.get(6);
    }
    public String getEmail()
    {
        return randomData.getRandomEmail();
    }
    public void getRoles()
    {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Id, _role);
        page.selectDropdownByIndex(role,Integer.parseInt(excelList.get(7)));
    }
    public void enterPrefix(String prefixToEnter)
    {
        page.enterText(prefix,prefixToEnter);
    }
    public void enterFirstName(String firstNameToEnter)
    {
        page.enterText(firstName,firstNameToEnter);
    }
    public void enterLastName(String lastNameToEnter)
    {
        page.enterText(lastName,lastNameToEnter);
    }
    public void enterEmail(String emailToEnter) {
        page.enterText(email,emailToEnter);
    }
    public void enterPassword(String passwordToEnter) {
        page.enterText(password,passwordToEnter);
    }
    public void enterConfirmPassWord(String confirmPasswordToEnter) {
        page.enterText(confirmPassword, confirmPasswordToEnter);
    }
    public void enterUserName(String userNameToEnter)
    {
        page.enterText(userName,userNameToEnter);
    }
    public void clickOnToastMessage() throws InterruptedException {
        page.clickOnElement(toastMessage);
    }
    public String getNewUserName() {
        return excelList.get(8);
    }
}
