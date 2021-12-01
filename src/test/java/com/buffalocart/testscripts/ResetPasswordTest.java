package com.buffalocart.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.buffalocart.automationcore.Base;
import com.buffalocart.listener.TestListener;
import com.buffalocart.pages.HomePage;
import com.buffalocart.pages.LoginPage;
import com.buffalocart.pages.ResetPasswordPage;
import com.buffalocart.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ResetPasswordTest extends Base {
    LoginPage login;
    ResetPasswordPage reset;
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    /*** Test Cases ***/
    @Test(priority = 5,enabled = true,description = "TC_005_Verify error message displyed on  Reset Password page with invalid email id",groups = {"Regression"})
    public void verifyErrorMessageOnResetPasswordPage() throws IOException {
        extentTest.get().assignCategory("Regression");
        login=new LoginPage(driver);
        reset = login.clickOnForgotPassword();
        extentTest.get().log(Status.PASS, "Successfully clicked on forgot password");
        String recoverEmail = reset.getRecoverEmailToEnter();
        reset.enterRecoverEmailToEnter(recoverEmail);
        extentTest.get().log(Status.PASS, "Recover email address entered successfully");
        reset.clickOnResetPasswordButton();
        extentTest.get().log(Status.PASS, "Successfully clicked on reset password button");
        String actualErrorMessage = reset.getActualErrorMessage();
        extentTest.get().log(Status.PASS, "Actual error message successfully captured");
        String expectedErrorMessage = reset.getExpectedErrorMessage();
        extentTest.get().log(Status.PASS, "Expected error message successfully captured");
        Assert.assertEquals(actualErrorMessage,expectedErrorMessage,"MISMATCH FOUND IN ERROR MESSAGE DISPLAYED WHEN INVALID RECOVER EMAIL IS ENTERED");
        extentTest.get().log(Status.PASS, "Verify error message displyed on  Reset Password page with invalid email id test case passed");
    }
}
