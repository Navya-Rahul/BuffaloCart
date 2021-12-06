package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class AddSalesCommissionAgentsPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public AddSalesCommissionAgentsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.ADD_SALES_COMMISSION_AGENTS_SHEET_NAME);
    /*** Web Elements ***/
    private final String _prefix = "surname";
    @FindBy(id=_prefix)
    private WebElement prefix;

    private final String _fName = "first_name";
    @FindBy(id=_fName)
    private WebElement fName;

    private final String _lName = "last_name";
    @FindBy(id=_lName)
    private WebElement lName;

    private final String _email = "email";
    @FindBy(id=_email)
    private WebElement email;

    private final String _contact = "contact_no";
    @FindBy(id=_contact)
    private WebElement contact;

    private final String _address = "address";
    @FindBy(id=_address)
    private WebElement address;

    private final String _percentage = "cmmsn_percent";
    @FindBy(id=_percentage)
    private WebElement percentage;

    private final String _saveButton = "//button[@class='btn btn-primary']";
    @FindBy(xpath=_saveButton)
    private WebElement saveButton;

    /*** User Action Methods ***/
    public String getPrefix() {
        return excelList.get(0);
    }

    public void enterPrefix(String prefixToEnter) {
        page.enterText(prefix,prefixToEnter);
    }

    public String getFirstName() {
        return excelList.get(1);
    }

    public void enterFirstName(String firstNameToEnter) {
        page.enterText(fName,firstNameToEnter);
    }

    public String getLastName() {
        return excelList.get(2);
    }

    public void enterLastName(String lastNameToEnter) {
        page.enterText(lName,lastNameToEnter);
    }

    public String getEmail() {
        return excelList.get(3);
    }

    public void enterEmail(String emailToEnter) {
        page.enterText(email,emailToEnter);
    }

    public String getContactNo() {
        return excelList.get(4);
    }

    public void enterContactNo(String contactNoToEnter) {
        page.enterText(contact,contactNoToEnter);
    }

    public String getAddress() {
        return excelList.get(5);
    }

    public void enterAddress(String addressToEnter) {
        page.enterText(address,addressToEnter);
    }

    public String getPercentage() {
        return excelList.get(6);
    }

    public void enterPercentage(String percentageToEnter) {
        page.enterText(percentage,percentageToEnter);
    }
    public SalesCommissionAgentsPage clickOnSaveButton() throws IOException {
        page.clickOnElement(saveButton);
        return new SalesCommissionAgentsPage(driver);
    }

    public String getExpectedAgent() {
        return excelList.get(7);
    }
}
