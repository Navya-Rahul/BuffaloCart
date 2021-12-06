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

public class UpdateSalesCommissionAgentsTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    AddUsersPage addUsers;
    UserManagementPage userManagement;
    RolesPage roles;
    SalesCommissionAgentsPage salesCommissionAgents;
    UpdateSalesCommissionAgentsPage updateSalesCommissionAgents;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 30, enabled = true, description = "TC_030_Verify Edit sales agent details",groups = {"Smoke","Regression"})
    public void verifyEditSalesAgentDetails() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        roles = new RolesPage(driver);
        addUsers = new AddUsersPage(driver);
        softAssert = new SoftAssert();
        updateSalesCommissionAgents = new UpdateSalesCommissionAgentsPage(driver);
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
        String salesAgentToEdit = salesCommissionAgents.getSalesAgentToEdit();
        extentTest.get().log(Status.PASS, "Successfully captured the sales agent to edit");
        updateSalesCommissionAgents = salesCommissionAgents.clickOnEditButton(salesAgentToEdit);
        extentTest.get().log(Status.PASS, "Successfully clicked on edit button");
        String newData = updateSalesCommissionAgents.getNewData();
        Thread.sleep(6000);
        updateSalesCommissionAgents.enterNewData(newData);
        extentTest.get().log(Status.PASS, "Successfully entered the new value");
        salesCommissionAgents = updateSalesCommissionAgents.clickOnUpdate();
        Thread.sleep(6000);
        List<ArrayList<String>> tableData = salesCommissionAgents.getTableData();
        boolean status = salesCommissionAgents.getTableContainsData(tableData,newData);
        softAssert.assertTrue(status,"ERROR : INVALID SALES COMMISSION AGENT");
        extentTest.get().log(Status.PASS, "User can successfully add sales commission agent");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Edit sales agent details test case passed");
    }
}
