package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class AddUsersTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    AddUsersPage addUsers;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 13,enabled = true,description = "TC_013_Verify the error message displayed without filling mandatory fields in add user form")
    public void verifyErrorMessageWithoutFillingMandatory() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
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
        addUsers = users.clickOnAddUsersButton();
        extentTest.get().log(Status.PASS, "Successfully navigated to add users page");
        String expectedErrorMessage = addUsers.getExpectedErrorMessage();
        extentTest.get().log(Status.PASS, "Expected error successfully captured");
        addUsers.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked on save button without filling mandatory fields");
        String actualErrorMessage = addUsers.getActualErrorMessage();
        extentTest.get().log(Status.PASS, "Actual error successfully captured");
        softAssert.assertEquals(actualErrorMessage,expectedErrorMessage,"ERROR : MISMATCH FOUND IN ERROR MESSAGE");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify the error message displayed without filling mandatory fields in add user form test case passed");
    }
    @Test(priority = 14,enabled = true,description = "TC_014_Verify user login with newly added user")
    public void verifyUserLoginWithNewlyAddedUser() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
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
        addUsers = users.clickOnAddUsersButton();
        extentTest.get().log(Status.PASS, "Successfully navigated to add users page");
        addUsers.enterPrefix(addUsers.getPrefix());
        addUsers.enterFirstName(addUsers.getFirstName());
        addUsers.enterLastName(addUsers.getLastName());
        addUsers.enterEmail(addUsers.getEmail());
        addUsers.getRoles();
        addUsers.enterUserName(addUsers.getExpectedUserName());
        addUsers.enterPassword(addUsers.getPassword());
        addUsers.enterConfirmPassWord(addUsers.getConfirmPassword());
        extentTest.get().log(Status.PASS, "Successfully entered the user details");
        addUsers.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked on save button");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        login.enterLoginUserName(addUsers.getExpectedUserName());
        login.enterLoginPassword(addUsers.getPassword());
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully logged in using new user credentials");
        String actualUserName= home.getUserName();
        extentTest.get().log(Status.PASS, "Actual user name generated");
        String expectedUserName= addUsers.getNewUserName();
        extentTest.get().log(Status.PASS, "expected user name generated");
        softAssert.assertEquals(actualUserName,expectedUserName,"ERROR : LOGIN FAILED");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user login with newly added user test passed");
    }
    @Test(priority = 15,enabled = true,description = "TC_015_Verify Add Users page title")
    public void verifyAddUsersPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
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
        addUsers = users.clickOnAddUsersButton();
        extentTest.get().log(Status.PASS, "Successfully navigated to add users page");
        String expectedTitle = addUsers.getExpectedAddUsersPageTitle();
        extentTest.get().log(Status.PASS, "Expected title successfully captured");
        String actualTitle = addUsers.getActualAddUsersPageTitle();
        extentTest.get().log(Status.PASS, "Actual title successfully captured");
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID ADD USERS PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Add Users page title test case passed");
    }
}
