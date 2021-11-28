package com.buffalocart.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtility {
    public static XSSFWorkbook wb;
    public static XSSFSheet sh;
    public static FileInputStream f;
    public List<String> readDataFromExcel(String filePath, String sheetName) throws IOException {
        String path = System.getProperty("user.dir")+ File.separator+ filePath;
        List<String> list = new ArrayList<String>();
        DataFormatter formatter = new DataFormatter();
        FileInputStream file = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        for (Row r : sheet) {
            for (Cell c : r) {
                list.add(formatter.formatCellValue(c));
            }
        }
        return list;
    }
}