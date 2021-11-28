package com.buffalocart.utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class PageUtility {
    Select select;
    List list;
    Alert alert;
    public void clickOnElement(WebElement element){
        element.click();
    }
    public void enterText(WebElement element,String value)
    {
        element.sendKeys(value);
    }
    public String getAttributeValue(WebElement element,String attribute)
    {
        return element.getAttribute(attribute);
    }
    public String getPageTitle(WebDriver driver)
    {
        return driver.getTitle();
    }
    public void doubleClickOnElement(WebDriver driver,WebElement element)
    {
        Actions action = new Actions(driver);
        action.doubleClick(element).build().perform();
    }
    public void rightClickOnElement(WebDriver driver,WebElement element)
    {
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
    }
    public void moveToElements(WebDriver driver,WebElement element)
    {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }
    public void dragAndDrop(WebDriver driver,WebElement dragElement,WebElement dropElement)
    {
        Actions action = new Actions(driver);
        action.dragAndDrop(dragElement,dropElement).build().perform();
    }
    public void clickAndHold(WebDriver driver,WebElement element)
    {
        Actions action = new Actions(driver);
        action.clickAndHold(element).dragAndDropBy(element,500,500).build().perform();
    }
    public void simpleAlert(WebDriver driver)
    {
        alert = driver.switchTo().alert();
        alert.accept();
    }
    public void confirmationAlertAccept(WebDriver driver)
    {
        alert = driver.switchTo().alert();
        alert.accept();
    }
    public void confirmationAlertDismiss(WebDriver driver)
    {
        alert = driver.switchTo().alert();
        alert.dismiss();
    }
    public void sendValuesToAlert(WebDriver driver,String dataToEnter)
    {
        alert = driver.switchTo().alert();
        alert.sendKeys(dataToEnter);
        alert.accept();
    }
    public String getAlertText(WebDriver driver)
    {
        alert = driver.switchTo().alert();
        String alertText = alert.getText();
        return alertText;
    }
    public boolean isElementSelected(WebElement element)
    {
        boolean status = element.isSelected();
        return status;
    }
    public boolean isElementDisplayed(WebElement element)
    {
        boolean status = element.isDisplayed();
        return status;
    }
    public boolean isElementEnabled(WebElement element)
    {
        boolean status = element.isEnabled();
        return status;
    }
    public void selectDropdownByIndex(WebElement element,int index)
    {
        select = new Select(element);
        select.selectByIndex(index);
    }
    public void selectDropdownByWebElement(WebElement element,String value)
    {
        select = new Select(element);
        select.selectByValue(value);
    }
    public void selectDropdownByValue(WebElement element,int value)
    {
        select = new Select(element);
        select.selectByIndex(value);
    }
    public List<WebElement> selectGetOption(WebElement element){
        select = new Select(element);
        list = new ArrayList();
        list.add(select.getOptions());
        return list;
    }
    public String getElementText(WebElement element)
    {
        return element.getText();
    }
}

