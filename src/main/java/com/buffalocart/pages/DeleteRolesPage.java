package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class DeleteRolesPage extends TestHelperUtility {
    WebDriver driver;

    /*** Class Constructor ***/
    public DeleteRolesPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    /*** Web Elements ***/
    private final String _deleteOkButton="//button[@class='swal-button swal-button--confirm swal-button--danger']";
    @FindBy(xpath = _deleteOkButton)
    private WebElement deleteOkButton;

    /*** User Action Methods ***/
    public RolesPage clickOnOkButton() throws IOException {
        page.clickOnElement(deleteOkButton);
        return new RolesPage(driver);
    }
}
