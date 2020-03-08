package util;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class ExcelWritter {


    public static void main(String args[]) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Students");
        Row header = spreadsheet.createRow(0);
        header.createCell(0).setCellValue("Name");
        header.createCell(1).setCellValue("Course");
        header.createCell(2).setCellValue("Batch");
        Row row1 = spreadsheet.createRow(1);
        row1.createCell(0).setCellValue("John Doe");
        row1.createCell(1).setCellValue("SDET-Java");
        row1.createCell(2).setCellValue(1);
        FileOutputStream fileOutputStream = null;
        try {
            File newExcelFile = new File("C:/Users/Beknazar/Desktop/Students.xlsx");
            fileOutputStream = new FileOutputStream(newExcelFile);
            workbook.write(fileOutputStream);
            System.out.println("Excel is created.");
                workbook.close();
                fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR WHILE CREATING EXCEL");
            throw new Exception(e);
        } finally {
            System.out.println("Closing resources..");
            workbook.close();
            fileOutputStream.close();
        }
    }
}
