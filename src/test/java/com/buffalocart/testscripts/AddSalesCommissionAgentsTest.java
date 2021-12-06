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

public class AddSalesCommissionAgentsTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    AddUsersPage addUsers;
    UserManagementPage userManagement;
    RolesPage roles;
    SalesCommissionAgentsPage salesCommissionAgents;
    AddSalesCommissionAgentsPage addSalesCommissionAgents;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 29,enabled = true,description = "TC_029_Verify  user can add sales  agent ",groups = {"Smoke","Sanity","Regression"})
    public void verifyUserCanAddSalesAgent() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Sanity");
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
        addSalesCommissionAgents = salesCommissionAgents.clickOnAddButton();
        Thread.sleep(5000);
        addSalesCommissionAgents.enterPrefix(addSalesCommissionAgents.getPrefix());
        addSalesCommissionAgents.enterFirstName(addSalesCommissionAgents.getFirstName());
        addSalesCommissionAgents.enterLastName(addSalesCommissionAgents.getLastName());
        addSalesCommissionAgents.enterEmail(addSalesCommissionAgents.getEmail());
        addSalesCommissionAgents.enterContactNo(addSalesCommissionAgents.getContactNo());
        addSalesCommissionAgents.enterAddress(addSalesCommissionAgents.getAddress());
        addSalesCommissionAgents.enterPercentage(addSalesCommissionAgents.getPercentage());
        salesCommissionAgents = addSalesCommissionAgents.clickOnSaveButton();
        extentTest.get().log(Status.PASS, "Successfully clicked on save button");
        Thread.sleep(6000);
        String salesAgentToSearch = addSalesCommissionAgents.getExpectedAgent();
        List<ArrayList<String>> tableData = salesCommissionAgents.getTableData();
        boolean status = salesCommissionAgents.getTableContainsData(tableData,salesAgentToSearch);
        softAssert.assertTrue(status,"ERROR : INVALID SALES COMMISSION AGENT");
        extentTest.get().log(Status.PASS, "User can successfully add sales commission agent");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify user login with newly added user test passed");
    }
}
