package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class RolesTest extends Base {
    LoginPage login;
    UsersPage users;
    HomePage home;
    SoftAssert softAssert;
    SignOutPage signOut;
    AddUsersPage addUsers;
    UserManagementPage userManagement;
    RolesPage roles;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(priority = 21,enabled = true,description = "TC_021_Verify Roles page title",groups = {"Regression"})
    public void verifyRolesPageTitle() throws IOException {
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
        roles = userManagement.clickOnRoleMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on roles tab");
        String actualTitle = roles.getActualRolesPageTitle();
        extentTest.get().log(Status.PASS, "Actual Roles page title successfully captured");
        String expectedTitle = roles.getExpectedRolesPageTitle();
        extentTest.get().log(Status.PASS, "Expected Roles page title successfully captured");
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID ROLES PAGE TITLE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify Roles page title test case passed");
    }
    @Test(priority = 27,enabled = true,description = "TC_027_Verify whether the added role in Role page is listed in roles field in user add page",groups = {"Regression"})
    public void verifyAddedRolesListedInRolesFiels() throws IOException {
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        userManagement = new UserManagementPage(driver);
        users = new UsersPage(driver);
        home = new HomePage(driver);
        signOut = new SignOutPage(driver);
        roles = new RolesPage(driver);
        softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        userManagement.clickOnUserManagementTab();
        extentTest.get().log(Status.PASS, "Successfully clicked on user management tab");
        users = userManagement.clickOnUserMenu();
        extentTest.get().log(Status.PASS, "Successfully clicked on user menu");
        addUsers = users.clickOnAddUsersButton();
        extentTest.get().log(Status.PASS, "Successfully navigated to add users page");
        String roleToSearch = roles.getEditRoleSearch();
        extentTest.get().log(Status.PASS, "Successfully captured the role to search");
        roles.clickOnDropdownArrow();
        boolean status = roles.getDropdownData(roleToSearch);
        extentTest.get().log(Status.PASS, "Successfully captured the values in role field");
        softAssert.assertTrue(status,"ERROR : FAILED TO ADD ROLES");
        softAssert.assertAll();
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify whether the added role in Role page is listed in roles field in user add page test case passed");
    }
}
