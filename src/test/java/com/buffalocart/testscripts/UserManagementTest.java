package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import com.buffalocart.pages.UserManagementPage;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class UserManagementTest extends Base {
    LoginPage login;
    UserManagementPage userManagement;
    HomePage home;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    /*** Test Cases ***/
    @Test(priority = 9,enabled = true,description = "TC_009_Verify the Usermangement sub tabs")
    public void verifyUserManagementSubTabs() throws IOException{
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        home = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        userManagement = new UserManagementPage(driver);
        SignOutPage signOut = new SignOutPage(driver);
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        extentTest.get().log(Status.PASS, "Successfully clicked on user management tab");
        List<String> actualTabValues = userManagement.getActualUserManagementTabValues();
        extentTest.get().log(Status.PASS, "Actual user Management Tab values successfully captured");
        List<String> expectedTabValues = userManagement.getExpectedUserManagementTabValues();
        extentTest.get().log(Status.PASS, "Expected user Management Tab values successfully captured");
        softAssert.assertEquals(actualTabValues,expectedTabValues,"MISMATCH FOUND IN USER MANAGEMENT SUB TAB VALUES");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify the Usermangement sub tabs test case passed");
    }
}
