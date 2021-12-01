package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.constants.Constants;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.SignOutPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class HomeTest extends Base {
    LoginPage login;
    HomePage home;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    /*** Test Cases ***/
    @Test(priority=6,enabled=true,description ="TC_006_Verify Home page title",groups = {"Regression"})
    public void verifyHomePageTitle() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        home=new HomePage(driver);
        SignOutPage signOut = new SignOutPage(driver);
        SoftAssert softAssert = new SoftAssert();
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        String actualTitle= home.getHomePageActualTitle();
        extentTest.get().log(Status.PASS, "Actual home page title is generated");
        String expectedTitle = home.getHomePageExpectedTitle();
        extentTest.get().log(Status.PASS, "Expected home page title generated");
        softAssert.assertEquals(actualTitle,expectedTitle,"ERROR : INVALID HOME PAGE TITLE PAGE FOUND");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "verify home page title test case passed");
    }
    @Test(priority = 7,enabled = true,description = "TC_007_Verify date displayed in home page",groups = {"Regression"})
    public void verifyDateInHomePage() throws IOException {
        extentTest.get().assignCategory("Regression");
        home=new HomePage(driver);
        login=new LoginPage(driver);
        SoftAssert softAssert = new SoftAssert();
        SignOutPage signOut = new SignOutPage(driver);
        List<String> loginList = login.getLoginData();
        login.enterLoginUserName(loginList.get(2));
        login.enterLoginPassword(loginList.get(3));
        login.clickOnLoginButton();
        extentTest.get().log(Status.PASS, "Successfully Logged in");
        String actualDate = home.getActualDateInHomePage();
        extentTest.get().log(Status.PASS, "Actual date is captured");
        String expectedDate = home.getActualDateInHomePage();
        extentTest.get().log(Status.PASS, "Expected date is captured");
        softAssert.assertEquals(actualDate,expectedDate,"ERROR : MISMATCH FOUND IN DATE DISPLAYED IN HOME PAGE");
        home.clickOnUserName();
        login = signOut.clickOnSignout();
        extentTest.get().log(Status.PASS, "Successfully signed out and navigated to login page");
        softAssert.assertAll();
        extentTest.get().log(Status.PASS, "Verify date displayed in home page test case is passed");
    }
}
