package util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtilsONE {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("/Users/test/Desktop/Student.xls");
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet2");// LOOK FOR THE NUMBER OF THE SHEET ON EXCEL SHEET DOWN BELOW
        Row row = sheet.getRow(0);// ROW
        Cell cell = row.getCell(1);//CELLs

        int rowAccount = sheet.getPhysicalNumberOfRows();

        for(int i=1; i<rowAccount; i++){// starts from 1 because is the number of the names
            String model = sheet.getRow(i).getCell(0).toString();// acess to model colunm #0
            String color = sheet.getRow(i).getCell(2).toString();// access to color colunm #1
            String year = sheet.getRow(i).getCell(1).toString();// access to a year colunm #2
            Double numYear = Double.parseDouble(year);
            if(model.equalsIgnoreCase("bmw"))
                model += "\t";
            if(numYear > 2018 && color.equalsIgnoreCase("black"))
                System.out.println(model + "\t | \t" + color+  "\t | \t " + year.substring(0, year.length()-2) + "\t | \t " + color );

        }

    }

}
