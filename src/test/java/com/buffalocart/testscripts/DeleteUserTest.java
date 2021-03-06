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

public class DeleteUserTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    AddUsersPage addUsers;
    UpdateUserPage updateUser;
    DeleteUserPage deleteUser;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 19, enabled = true, description = "TC_017_Verify user can delete a user", groups = {"Sanity","Regression"})
    public void verifyUserCanDeleteUser() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
        updateUser = new UpdateUserPage(driver);
        deleteUser = new DeleteUserPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        users = userManagement.clickOnUserMenu();
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully clicked on users tab");
        String userToDelete = users.getUserToBeDeleted();
        extentTest.get().log(Status.PASS, "Successfully captured the user to delete");
        deleteUser = users.clickOnDeleteButton(userToDelete);
        extentTest.get().log(Status.PASS, "Successfully clicked on delete button");
        Thread.sleep(6000);
        users = deleteUser.clickOnOkButton();
        Thread.sleep(6000);
        List<ArrayList<String>> data = users.getTableData();
        boolean value =users.getTableContainsData(data,users.getUserToBeDeleted());
        softAssert.assertFalse(value,"ERROR : USER DELETION FAILED");
        extentTest.get().log(Status.PASS, "Successfully deleted the user");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user can delete a user test case passed");
    }
}
