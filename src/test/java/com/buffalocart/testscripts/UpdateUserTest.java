package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    AddUsersPage addUsers;
    UpdateUserPage updateUser;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 17, enabled = true, description = "TC_017_Verify Edit User Page Title", groups = {"Sanity","Regression"})
    public void verifyEditUserPageTitle() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
        updateUser = new UpdateUserPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        users = userManagement.clickOnUserMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on users tab");
        String userName = updateUser.getEditUserSearch();
        System.out.println(userName);
        Thread.sleep(6000);
        updateUser = users.clickOnEditButton(userName);
        extentTest.get().log(Status.PASS, "Successfully captured username to search");
        String actualTitle = updateUser.getActualEditUserPageTitle();
        extentTest.get().log(Status.PASS, "Successfully captured Actual Edit User Page Title");
        String expectedTitle = updateUser.getExpectedEditUserPageTitle();
        extentTest.get().log(Status.PASS, "Successfully captured Expected Edit User Page Title");
        softAssert.assertEquals(actualTitle, expectedTitle, "ERROR : INVALID EDIT USER PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Edit User Page Title test case passed");
    }
    @Test(priority = 18,enabled = true,description = "TC_018_Verify  user can edit the user details",groups = {"Regression"})
    public void verifyUserCanEditTheUserDetails() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
        updateUser = new UpdateUserPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        users = userManagement.clickOnUserMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on users tab");
        String userName = updateUser.getEditUserSearch();
        extentTest.get().log(Status.PASS, "Successfully captured username to search");
        Thread.sleep(6000);
        updateUser = users.clickOnEditButton(userName);
        extentTest.get().log(Status.PASS, "Successfully clicked on edit button");
        String newData = updateUser.getNewDataToEdit();
        updateUser.setValueEmail(newData);
        extentTest.get().log(Status.PASS, "Edited email for a user");
        users=updateUser.clickOnUpdateButton();
        extentTest.get().log(Status.PASS, "Successfully clicked Update button");
        Thread.sleep(6000);
        List<ArrayList<String>> data = users.getTableData();
        users.getTableContainsData(data,updateUser.getNewDataToEdit());
        extentTest.get().log(Status.PASS, "Checked for the updated value in table data");
        Thread.sleep(6000);
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify  user can edit the user details test case passed");
    }
}
