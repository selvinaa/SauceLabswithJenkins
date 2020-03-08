package util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {


//          How do you work with Excel files? How do use for automation?
//
//          Today we will use Apache POI libraries to create, read, write, and modify Excel files using Java
//
//          Apache POI is a popular API that allows programmers to create, modify, and display MS Office files using Java programs.
//          It is an open source library developed and distributed by Apache Software Foundation
//          to design or modify Microsoft Office files using Java program.
//          It contains classes and methods to decode the user input data or a file into MS Office documents.

        public static void main(String args []) throws Exception {
            File excelFile = new File("C:/Users/Beknazar/Desktop/SampleData/Employees.xlsx");
            FileInputStream fileInputStream = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet spreadsheet = workbook.getSheet("employees");
            int numberOfRows = spreadsheet.getPhysicalNumberOfRows();
            for(int rowIndex = 0; rowIndex < numberOfRows; rowIndex++){
                Row row = spreadsheet.getRow(rowIndex);
                for(int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++){
                    Cell cell1 = row.getCell(cellIndex);
                    System.out.print(cell1+" | ");
                }
                System.out.println();
            }
        }
    }


