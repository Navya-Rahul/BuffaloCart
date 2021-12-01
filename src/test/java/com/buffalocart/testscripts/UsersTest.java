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
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 10,enabled = true,description = "TC_010_Verify users page title",groups = {"Regression"})
    public void verifyUsersPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        softAssert = new SoftAssert();
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
    @Test(priority = 11,enabled = true,description = "TC_011_Verify user search with valid data",groups = {"Smoke","Sanity","Regression"})
    public void verifyUserSearchWithValidData() throws IOException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        users.clickOnUserMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on users tab");
        String expectedSearchResult = users.getExpectedSearchData();
        extentTest.get().log(Status.PASS, "Expected user search data successfully captured");
        users.enterDataOnSearchBox(expectedSearchResult);
        extentTest.get().log(Status.PASS, "Successfully data entered on search box");
        String actualSearchResult = users.getActualSearchData();
        extentTest.get().log(Status.PASS, "Actual user search data successfully captured");
        softAssert.assertEquals(actualSearchResult,expectedSearchResult,"ERROR : INVALID SEARCH RESULT FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user search with valid data test case passed");
    }
    @Test(priority = 12,enabled = true,description = "TC_012_Verify message displayed by user search with invalid data",groups = {"Smoke","Regression"})
    public void verifyMessageInUserSearchWithInvalidData() throws IOException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        users.clickOnUserMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on users tab");
        String data = users.getInvalidSearchData();
        users.enterDataOnSearchBox(data);
        extentTest.get().log(Status.PASS, "Invalid data entered on search box");
        String expectedMessage = users.getExpectedMessage();
        extentTest.get().log(Status.PASS, "Expected Message successfully captured");
        String actualMessage = users.getActualMessage();
        extentTest.get().log(Status.PASS, "Actual Message successfully captured");
        softAssert.assertEquals(actualMessage,expectedMessage,"ERROR : INVALID SEARCH RESULT FOUND WHILE SEARCHING WITH INVALID USER DATA");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify message displayed by user search with invalid data test case passed");
    }
}
