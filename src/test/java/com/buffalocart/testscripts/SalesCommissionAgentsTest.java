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

public class SalesCommissionAgentsTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    AddUsersPage addUsers;
    UserManagementPage userManagement;
    RolesPage roles;
    SalesCommissionAgentsPage salesCommissionAgents;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 28,enabled = true,description = "TC_028_Verify  Sales Commission Agents page title",groups = {"Regression"})
    public void verifySalesCommissionAgentsPageTitle() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        roles = new RolesPage(driver);
        addUsers = new AddUsersPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        extentTest.get().log(Status.PASS, "Successfully clicked on user management tab");
        salesCommissionAgents = userManagement.clickOnSalesCommissionMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on sales commission agents tab");
        String actualTitle = salesCommissionAgents.getActualSalesCommissionAgentsPageTitle();
        extentTest.get().log(Status.PASS, "Actual sales commission agents page title successfully captured");
        String expectedTitle = salesCommissionAgents.getExpectedSalesCommissionAgentsPageTitle();
        extentTest.get().log(Status.PASS, "Expected sales commission agents page title successfully captured");
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID ROLES PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Roles page title test case passed");

    }
}
