package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class SignOutTest extends Base {
    SignOutPage signOut;
    LoginPage login;
    HomePage home;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    /*** Test Cases ***/
    @Test(priority = 8,enabled = true,description = "TC_008_Verify whether user is navigating to login page by clicking on Sign out button",groups = {"Smoke","Regression"})
    public void verifyTheNavigationOfSignOutButton() throws IOException, InterruptedException {
        extentTest.get().assignCategory("Smoke");
        extentTest.get().assignCategory("Regression");
        login = new LoginPage(driver);
        signOut = new SignOutPage(driver);
        home = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        home.clickOnEndTourButton();
        home.clickOnUserName();
        extentTest.get().log(Status.PASS, "Successfully clicked on username");
        login = signOut.clickOnSignout();
        //Thread.sleep(2000);
        String actualTitle = login.getActualLoginPageTitle();
        String expectedTitle = login.getExpectedLoginPageTitle();
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : NAVIGATION TO LOGIN PAGE FAILED");
        extentTest.get().log(Status.PASS, "Successfully navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify whether user is navigating to login page by clicking on Sign out button test case passed");
    }
}
