package com.buffalocart.pages;

import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class SignOutPage extends TestHelperUtility {
    WebDriver driver;
    /*** Class Constructor ***/
    public SignOutPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*** Web Elements ***/
    private final String _signoutButton = "//div[@class='pull-right']//a";
    @FindBy(xpath=_signoutButton)
    private WebElement signoutButton;

    /*** User Action Methods ***/
    public LoginPage clickOnSignout() throws IOException {
        page.clickOnElement(signoutButton);
        return new LoginPage(driver);
    }
}
