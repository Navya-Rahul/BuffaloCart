package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class UsersTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    UserManagementPage userManagement;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 10,enabled = true,description = "TC_010_Verify users page title")
    public void verifyUsersPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        SignOutPage signOut = new SignOutPage(driver);
        SoftAssert softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        users.clickOnUserMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on users tab");
        String actualTitle = users.getActualUsersPageTitle();
        extentTest.get().log(Status.PASS, "Actual users page title successfully captured");
        String expectedTitle = users.getExpectedUsersPageTitle();
        extentTest.get().log(Status.PASS, "Expected users page title successfully captured");
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID USERS PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify users page title test case passed");
    }

}
