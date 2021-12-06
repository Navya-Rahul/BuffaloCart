package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class LoginTest extends Base {
    LoginPage login;
    HomePage home;
    SignOutPage signOut;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    /*** Test Cases ***/
    @Test(priority=1,enabled=true,description ="TC_001_Verify Login page title" ,groups = {"Regression"})
    public void verifyLoginPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        home=new HomePage(driver);
        String actualTitle= login.getLoginPageActualTitle();
        extentTest.get().log(Status.PASS, "Actual login page title generated");
        List<String> loginList = login.getLoginData();
        String expectedTitle = loginList.get(0);
        extentTest.get().log(Status.PASS, "Expected login page title generated");
        Assert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID LOGIN PAGE TITLE PAGE FOUND");
        extentTest.get().log(Status.PASS, "verify login page title test case passed");
    }
    @Test(priority = 2,enabled = true,description = "TC_002_Verify user login with valid user credentials",groups = {"Regression","Smoke"})
    public void verifyUserLoginWithValidUserCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");
        extentTest.get().assignCategory("Smoke");
        login=new LoginPage(driver);
        home=new HomePage(driver);
        signOut = new SignOutPage(driver);
        SoftAssert softAssert=new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        extentTest.get().log(Status.PASS, "User Name is entered");
        login.enterLoginPassword(loginList.get(3));
        extentTest.get().log(Status.PASS, "Password is entered");
        login.clickOnLoginButton();
        home.clickOnEndTourButton();
        String actualUserName= home.getUserName();
        extentTest.get().log(Status.PASS, "Actual user name generated");
        String expectedUserName=loginList.get(4);
        extentTest.get().log(Status.PASS, "expected user name generated");
        softAssert.assertEquals(actualUserName,expectedUserName,"ERROR : LOGIN FAILED");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user login with valid user credentials test passed");
    }
    @Test(priority = 3,enabled = true,description = "TC_003_Verify the error message displayed for user login with invalid credentials",groups = {"Regression"})
    public void verifyErrorMessageWithInvalidCredentials() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(5));
        extentTest.get().log(Status.PASS, "User Name is entered");
        login.enterLoginPassword(loginList.get(6));
        extentTest.get().log(Status.PASS, "Password is entered");
        login.clickOnLoginButton();
        String actualErrorMessage = login.getErrorMessage();
        extentTest.get().log(Status.PASS, "Actual error message captured");
        String expectedErrorMessage = loginList.get(1);
        extentTest.get().log(Status.PASS, "Expected error message captured");
        Assert.assertEquals(expectedErrorMessage,actualErrorMessage,"ERROR : MISMATCH FOUND IN ERROR MESSAGE DISPLAYED WHEN INVALID USER CREDENTIALS ARE ENTERED");
        extentTest.get().log(Status.PASS, "Verify the error message displayed for user login with invalid credentials test case passed");
    }
    @Test(priority = 5,enabled = true,description = "TC_004_Verify whether the user is able to click on 'Remember me' checkbox")
    public void verifyRememberMeCheckbox() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        login.clickOnCheckBox();
        extentTest.get().log(Status.PASS, "Successfully clicked on remember me checkbox");
        boolean checkboxStatus = login.getCheckboxStatus();
        extentTest.get().log(Status.PASS, "Status of the remember me checkbox successfully captured");
        Assert.assertTrue(checkboxStatus,"ERROR : CHECKBOX IS NOT SELECTED");
        extentTest.get().log(Status.PASS, "Verify whether the user is able to click on 'Remember me' checkbox test case passed");
    }
}
