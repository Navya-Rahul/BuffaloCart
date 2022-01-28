package com.buffalocart.utilities;

import org.openqa.selenium.WebDriver;

public class TestHelperUtility {
    WebDriver driver;
    public PageUtility page = new PageUtility();
    public ExcelUtility excel = new ExcelUtility();
    public WaitUtility wait = new WaitUtility(driver);
    public RandomDataUtility randomData = new RandomDataUtility();
    public TableUtility tableUtility = new TableUtility();
}
