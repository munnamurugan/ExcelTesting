package org.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class WebdataToExcel {
	
	@Test
	public void excelWrite() throws IOException {
		File file = new File("D:\\Murugan\\Selenium driver files\\chromedriver.exe");
		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet("Sheet2");
		Row createRow = sheet.createRow(1);
		Cell createCell = createRow.createCell(3);
		createCell.setCellValue("Murugan Sivam");
		
		FileOutputStream fo =new FileOutputStream(file);
		wb.write(fo);
		wb.close();
	}

}
