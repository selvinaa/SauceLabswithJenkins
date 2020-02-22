package util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {
    public static void main(String[] args) throws IOException {
        FileInputStream file = new FileInputStream("/Users/test/Desktop/Student.xls");
        Workbook workbook = new HSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        Row row = sheet.getRow(0);// ROW
        Cell cell = row.getCell(1);//CELLs

        int rowAccount = sheet.getPhysicalNumberOfRows();

        for(int i=1; i<rowAccount; i++){// starts from 1 because is the number of the names
            String name = sheet.getRow(i).getCell(0).toString();
            String group = sheet.getRow(i).getCell(1).toString();
            double groupNum = Double.parseDouble(group);
            if(groupNum % 5==0)
                System.out.println(name);
           //System.out.println(sheet.getRow(i).getCell(1).toString());// from sheet get row and cell number
            //System.out.println(cell);
        }

        //System.out.println(cell.toString());

    }

}
