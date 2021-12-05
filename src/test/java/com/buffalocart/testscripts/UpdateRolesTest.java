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

public class UpdateRolesTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    RolesPage roles;
    UpdateRolesPage updateRoles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 24,enabled = true,description = "TC_024_Verify Edit Role page title",groups = {"Smoke","Sanity","Regression"})
    public void verifyEditRolePageTitle() throws IOException, InterruptedException {
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
        extentTest.get().log(Status.PASS, "Successfully clicked on user management tab");
        roles = userManagement.clickOnRoleMenu();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to roles page");
        updateRoles=roles.clickOnEditButton(roles.getEditRoleSearch());
        extentTest.get().log(Status.PASS, "Successfully clicked on edit button");
        String actualTitle= updateRoles.getActualEditRolesPageTitle();
        extentTest.get().log(Status.PASS, "Actual edit roles page title successfully captured");
        String expectedTitle=updateRoles.getExpectedEditRolesPageTitle();
        extentTest.get().log(Status.PASS, "Expected edit roles page title successfully captured");
        softAssert.assertEquals(actualTitle, expectedTitle, "ERROR : INVALID EDIT ROLES PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Edit Role page title test case passed");
    }
    @Test(priority = 25,enabled = true,description = "TC_025_Verify user can update  a role ",groups = {"Smoke","Sanity","Regression"})
    public void verifyUserCanUpdateRole() throws IOException {
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
        extentTest.get().log(Status.PASS, "Successfully clicked on user management tab");
        roles = userManagement.clickOnRoleMenu();
        extentTest.get().log(Status.PASS, "clicked on user tab and redirected to roles page");
        updateRoles=roles.clickOnEditButton(roles.getEditRoleSearch());
        extentTest.get().log(Status.PASS, "Successfully clicked on edit button");
        updateRoles.clickOnSelectUser();
        roles = updateRoles.clickOnUpdateButton();
        extentTest.get().log(Status.PASS, "Successfully clicked on update button");
        updateRoles = roles.clickOnEditButton(roles.getEditRoleSearch());
        String actualStatus = updateRoles.getActualSelectUser();
        extentTest.get().log(Status.PASS, "Actual status of the updated data successfully captured");
        String expectedStatus = updateRoles.getExpectedSelectUser();
        extentTest.get().log(Status.PASS, "Expected status of the updated data successfully captured");
        softAssert.assertEquals(actualStatus,expectedStatus,"ERROR : FAILED TO UPDATE ROLES");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user can update  a role test case passed");
    }
}
