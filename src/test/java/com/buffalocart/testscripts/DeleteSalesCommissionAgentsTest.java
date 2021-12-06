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

public class DeleteSalesCommissionAgentsTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    AddUsersPage addUsers;
    UpdateUserPage updateUser;
    DeleteUserPage deleteUser;
    SalesCommissionAgentsPage salesCommissionAgents;
    DeleteSalesCommissionAgentsPage deleteSalesCommissionAgents;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 31, enabled = true, description = "TC_031_Verify user can delete a Sales Commission Agents", groups = {"Sanity","Regression"})
    public void verifyUserCanDeleteSalesAgent() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Sanity");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        deleteSalesCommissionAgents = new DeleteSalesCommissionAgentsPage(driver);
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
        String salesAgentToDelete = salesCommissionAgents.getSalesAgentToDelete();
        extentTest.get().log(Status.PASS, "Successfully captured the sales agent to edit");
        deleteSalesCommissionAgents = salesCommissionAgents.clickOnDeleteButton(salesAgentToDelete);
        extentTest.get().log(Status.PASS, "Successfully clicked on edit button");
        salesCommissionAgents = deleteSalesCommissionAgents.clickOnOkButton();
        Thread.sleep(6000);
        List<ArrayList<String>> tableData = salesCommissionAgents.getTableData();
        boolean status = salesCommissionAgents.getTableContainsData(tableData,salesAgentToDelete);
        softAssert.assertFalse(status,"ERROR : INVALID SALES COMMISSION AGENT");
        extentTest.get().log(Status.PASS, "User can successfully add sales commission agent");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Edit sales agent details test case passed");
    }
}
