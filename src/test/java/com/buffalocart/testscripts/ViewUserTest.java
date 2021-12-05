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

public class ViewUserTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    UserManagementPage userManagement;
    AddUsersPage addUsers;
    UpdateUserPage updateUser;
    ViewUserPage viewUser;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 20, enabled = true, description = "TC_020_Verify the details displayed on view user page", groups = {"Regression"})
    public void verifyUserCanViewTheUserDetails() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        addUsers = new AddUsersPage(driver);
        updateUser = new UpdateUserPage(driver);
        viewUser = new ViewUserPage(driver);
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
        String dataToView = users.getUserToView();
        extentTest.get().log(Status.PASS, "Successfully captured the user to view");
        viewUser = users.clickOnViewButton(dataToView);
        extentTest.get().log(Status.PASS, "Successfully clicked on view button");
        String actualUserName = viewUser.getActualProfileUserName();
        extentTest.get().log(Status.PASS, "Successfully captured the actual profile user name");
        String expectedUserName = viewUser.getExpectedProfileUserName();
        extentTest.get().log(Status.PASS, "Successfully captured the expected profile user name");
        softAssert.assertEquals(actualUserName,expectedUserName,"ERROR : UNABLE TO VIEW USER DETAILS");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify the details displayed on view user page test case passed");
    }
}
