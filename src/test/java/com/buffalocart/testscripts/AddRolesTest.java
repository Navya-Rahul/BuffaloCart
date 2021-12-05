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

public class AddRolesTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    RolesPage roles;
    AddRolesPage addRoles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 22,enabled = true,description = "TC_022_Verify Add Roles page title",groups = {"Regression"})
    public void verifyAddRolesPageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        roles = new RolesPage(driver);
        addRoles = new AddRolesPage(driver);
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
        extentTest.get().log(Status.PASS, "Successfully clicked on roles tab");
        addRoles = roles.clickOnAddRolesButton();
        extentTest.get().log(Status.PASS, "Successfully clicked on add roles button");
        String actualTitle = addRoles.getActualAddRolesPage();
        extentTest.get().log(Status.PASS, "Actual Add Roles page title successfully captured");
        String expectedTitle = addRoles.getExpectedAddRolesPageTitle();
        extentTest.get().log(Status.PASS, "Expected Add Roles page title successfully captured");
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID ADD ROLES PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Add Roles page title test case passed");
    }
    @Test(priority = 23,enabled = true,description = "TC_023_Verify user can add roles ",groups = {"Smoke","Sanity","Regression"})
    public void verifyUserCanAddRoles() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        roles = new RolesPage(driver);
        addRoles = new AddRolesPage(driver);
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
        extentTest.get().log(Status.PASS, "Successfully clicked on roles tab");
        addRoles = roles.clickOnAddRolesButton();
        String dataToEnter = addRoles.getRoleToEnter();
        extentTest.get().log(Status.PASS, "Successfully clicked on add roles button");
        addRoles.enterRole(dataToEnter);
        extentTest.get().log(Status.PASS, "Successfully enter the new role");
        roles = addRoles.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully added new role and navigated to roles page");
        roles.enterTextOnSearchBox(dataToEnter);
        Thread.sleep(6000);
        String roleToSearch = addRoles.getExpectedRole();
        List<ArrayList<String>> tableData = roles.getTableData();
        boolean status = roles.getTableContainsData(tableData,roleToSearch);
        softAssert.assertTrue(status,"ERROR : INVALID ROLE");
        extentTest.get().log(Status.PASS, "User can successfully add roles");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user can add roles  test passed");
    }
}
