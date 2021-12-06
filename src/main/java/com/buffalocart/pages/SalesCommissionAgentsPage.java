package com.buffalocart.pages;

import com.buffalocart.constants.Constants;
import com.buffalocart.utilities.TestHelperUtility;
import com.buffalocart.utilities.WaitUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesCommissionAgentsPage extends TestHelperUtility {
    WebDriver driver;
    boolean values;

    /*** Class Constructor ***/
    public SalesCommissionAgentsPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    List<String> excelList = excel.readDataFromExcel(Constants.FILE_PATH, Constants.SALES_COMMISSION_AGENTS_SHEET_NAME);

    /*** Web Elements ***/
    private final String _addButton = "//button[@class='btn btn-primary btn-modal pull-right']";
    @FindBy(xpath = _addButton)
    private WebElement addButton;

    public final String _editButton = "//i[@class='glyphicon glyphicon-edit']";
    @FindBy(xpath = _editButton)
    private WebElement editButton;

    private final String _deleteButton = "//button[@class='btn btn-xs btn-danger delete_commsn_agnt_button']";
    @FindBy(xpath = _deleteButton)
    private WebElement deleteButton;

    private final String _rowElement = "//table[@id='sales_commission_agent_table']//tbody//tr";
    @FindBy(xpath = _rowElement)
    private List<WebElement> rowElement;

    private final String _colElement = "//table[@id='sales_commission_agent_table']//tbody//tr//td";
    @FindBy(xpath = _colElement)
    private List<WebElement> colElement;

    /*** User Action Mehods ***/
    public String getActualSalesCommissionAgentsPageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedSalesCommissionAgentsPageTitle() {
        return excelList.get(0);
    }

    public AddSalesCommissionAgentsPage clickOnAddButton() throws IOException {
        page.clickOnElement(addButton);
        return new AddSalesCommissionAgentsPage(driver);
    }

    public List<ArrayList<String>> getTableData() {
        return tableUtility.getGridData(rowElement, colElement);
    }

    public boolean getTableContainsData(List<ArrayList<String>> tableData, String salesAgentToSearch) {

        values = false;
        for (int i = 0; i < tableData.size(); i++) {
            if (tableData.get(i).contains(salesAgentToSearch)) {
                values = true;
            }
        }
        return values;
    }

    public String getSalesAgentToEdit() {
        return excelList.get(1);
    }

    public UpdateSalesCommissionAgentsPage clickOnEditButton(String salesAgentToEdit) throws IOException {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _editButton);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (values == false) {
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);
                    if (values == false) {

                        String tData = data.getText();

                        if (tData.contains(salesAgentToEdit)) {
                            editButton = driver.findElement(
                                    By.xpath("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) + "]//td[6]//i[@class='glyphicon glyphicon-edit']"));
                            page.clickOnElement(editButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        }
        return new UpdateSalesCommissionAgentsPage(driver);
    }

    public String getSalesAgentToDelete() {
        return excelList.get(2);
    }

    public DeleteSalesCommissionAgentsPage clickOnDeleteButton(String salesAgentToDelete) throws IOException {
        wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath, _deleteButton);
        List<ArrayList<WebElement>> actionData = tableUtility.actionData(rowElement, colElement);
        if (!values)
            for (int i = 0; i < actionData.size(); i++) {
                for (int j = 0; j < actionData.get(0).size(); j++) {
                    WebElement data = actionData.get(i).get(j);

                    if (!values) {
                        String tData = data.getText();
                        if (tData.contains(salesAgentToDelete)) {
                            deleteButton = driver.findElement(
                                    By.xpath(("//table[@id='sales_commission_agent_table']//tbody//tr[" + (i + 1) + "]//td[6]//button[2]")));
                            page.clickOnElement(deleteButton);
                            values = true;
                            break;
                        }
                    }
                }

            }
        return new DeleteSalesCommissionAgentsPage(driver);
    }
}
