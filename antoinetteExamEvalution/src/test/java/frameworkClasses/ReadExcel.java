package frameworkClasses;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel extends BasePage {
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			DataFormatter formatter =new DataFormatter();
			XSSFRow row =sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			
			data = new String[noOfRows -1][noOfCols];
			for (int i =1; i < noOfRows; i++) {
				for (int j = 0; j< noOfCols; j++) {
					data[i - 1][j] = formatter.formatCellValue(sh.getRow(i).getCell(j));
					
				}
			}
		}
		catch (Exception e) {
			System.out.println("The Exception is: " + e.getMessage());
		}
		return data;
	}

}
