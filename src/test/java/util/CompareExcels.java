package util;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class CompareExcels {


        @Test
        public void test() throws Exception {
            List<List<String>> expectedExcel = getExcelData("/Users/test/Desktop/ExcelFileReader/ExpectedData.xlsx");
            List<List<String>> actualExcel = getExcelData("/Users/test/Desktop/ExcelFileReader/ActualData.xlsx");

            for(int i = 0; i < expectedExcel.size(); i++){
                Assert.assertEquals("Row number: "+(i+1),expectedExcel.get(i), actualExcel.get(i));
            }
        }

        public List<List<String>> getExcelData(String path) throws Exception{
            List<List<String>> content = new ArrayList<List<String>>();

            File excelFile = new File(path);
            FileInputStream fileInputStream = new FileInputStream(excelFile);

            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet spreadsheet = workbook.getSheetAt(0);

            int rowNum = spreadsheet.getPhysicalNumberOfRows();
            for(int i = 0; i < rowNum; i++){
                List<String> line = new ArrayList<String>();
                Row row = spreadsheet.getRow(i);
                for(int j = 0; j < row.getPhysicalNumberOfCells(); j++){
                    line.add(row.getCell(j).toString());
                }
                content.add(line);

            }
            return content;
        }
    }




