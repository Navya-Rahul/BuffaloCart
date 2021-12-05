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

public class DeleteRolesTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    AddUsersPage addUsers;
    UpdateUserPage updateUser;
    DeleteUserPage deleteUser;
    RolesPage roles;
    DeleteRolesPage deleteRoles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 26, enabled = true, description = "TC_026_Verify user can delete a role from the list", groups = {"Regression"})
    public void verifyUserCanDeleteRole() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
        updateUser = new UpdateUserPage(driver);
        deleteUser = new DeleteUserPage(driver);
        deleteRoles = new DeleteRolesPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        roles = userManagement.clickOnRoleMenu();
        Thread.sleep(6000);
        extentTest.get().log(Status.PASS, "Successfully clicked on roles tab");
        String roleToDelete = roles.getRoleToBeDeleted();
        extentTest.get().log(Status.PASS, "Successfully captured the role to delete");
        deleteRoles = roles.clickOnDeleteButton(roleToDelete);
        extentTest.get().log(Status.PASS, "Successfully clicked on delete button");
        Thread.sleep(6000);
        roles = deleteRoles.clickOnOkButton();
        Thread.sleep(6000);
        List<ArrayList<String>> data = roles.getTableData();
        boolean value =roles.getTableContainsData(data,roleToDelete);
        softAssert.assertFalse(value,"ERROR : ROLE DELETION FAILED");
        extentTest.get().log(Status.PASS, "Successfully deleted the role");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user can delete a role test case passed");
    }
}
